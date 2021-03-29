package com.davidalmarinho.game_objects.components;

import com.davidalmarinho.game_objects.GameObject;
import com.davidalmarinho.input.MouseInput;
import com.davidalmarinho.main.Camera;
import com.davidalmarinho.main.GameManager;
import com.davidalmarinho.scenes.Scene;
import com.davidalmarinho.utils.Constants;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

public class SnapToGrid extends Component {
    private final int tileWidth, tileHeight;
    private int x, y;
    private boolean mayPlace;
    private float timePlace;

    public SnapToGrid(int tileWidth, int tileHeight) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    @Override
    public void update(float dt) {
        MouseInput mouseInput = GameManager.getInstance().mouseInput;

        Camera camera = GameManager.getInstance().getCurrentScene().camera;

        int xMouse = (int) (mouseInput.mousePosition.x + camera.position.x);
        int yMouse = (int) (mouseInput.mousePosition.y + camera.position.y);

        x = xMouse / tileWidth * tileWidth;
        y = yMouse / tileHeight * tileHeight;

        // Update positions
        this.gameObject.transform.position.x = x;
        this.gameObject.transform.position.y = y;

        // Check if we already have a game object in the current position
        if (gameObject.getComponent(Sprite.class) != null) { // Do just if our game object has Sprite component
            mayPlace = true;
            Scene levelEditorScene = GameManager.getInstance().getCurrentScene();
            for (GameObject g : levelEditorScene.gameObjects) {
                if (((int) (g.transform.position.x / Constants.TILE_SIZE)
                        == (int) (gameObject.transform.position.x / Constants.TILE_SIZE))
                        && ((int) (g.transform.position.y / Constants.TILE_SIZE)
                        == (int) (gameObject.transform.position.y / Constants.TILE_SIZE))) {
                    mayPlace = false;
                    break;
                }
            }
        } else {
            mayPlace = false;
        }

        float limitTimePlace = 0.001f;

        // If we click just a time, ignore times' rules
        if (mouseInput.isButtonDown(MouseEvent.BUTTON1)) {
            generatedGameObject();

        } else if (mouseInput.isButton(MouseEvent.BUTTON1)) {
            timePlace += dt;

            if (timePlace >= limitTimePlace) {
                timePlace = .0f;
                generatedGameObject();
            }
        } else {
            timePlace = .0f;
        }
    }

    private void generatedGameObject() {
        // Copy the current gameObject to another gameObject, by a properly way to don't make weird bugs
        GameObject gameObject = this.gameObject.copy();
        if (mayPlace) {
            GameManager.getInstance().getCurrentScene().addGameObject(gameObject);
        }
    }

    @Override
    public void render(Graphics2D g2) {
        Camera camera = GameManager.getInstance().getCurrentScene().camera;

        int x = (int) (this.x - camera.position.x);
        int y = (int) (this.y - camera.position.y);
        if (gameObject.getComponent(Sprite.class) != null) {
            // Create a alpha composite to change the opacity of the image
            float alpha = .4f;
            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
            g2.setComposite(alphaComposite);
            // Affine Transform o scale the image
            AffineTransform affineTransform = new AffineTransform();
            affineTransform.setToIdentity(); // Reset AffineTransform
            affineTransform.translate(x, y);
            affineTransform.scale(Constants.GAME_SCALE, Constants.GAME_SCALE);
            // Draw a white rect around the image and turn the line
            g2.setColor(Color.WHITE);
            float thickness = 1.5f;
            g2.setStroke(new BasicStroke(thickness)); // Turn the line more thicker
            g2.drawRect((int) affineTransform.getTranslateX(), (int) affineTransform.getTranslateY(),
                    tileWidth, tileHeight);
            g2.setStroke(new BasicStroke());
            // Draw a image with less opacity to know where we will put it
            g2.drawImage(gameObject.getComponent(Sprite.class).image, affineTransform, null);
            // Reset alpha composite
            alpha = 1.0f;
            alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
            g2.setComposite(alphaComposite);

        } else {
            g2.setColor(Color.RED);
            g2.fillRect(x, y, Constants.TILE_SIZE, Constants.TILE_SIZE);
        }
    }

    @Override
    public Component copy() {
        return null;
    }
}
