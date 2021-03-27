package com.davidalmarinho.game_objects.components;

import com.davidalmarinho.game_objects.GameObject;
import com.davidalmarinho.input.MouseInput;
import com.davidalmarinho.main.Camera;
import com.davidalmarinho.main.GameManager;
import com.davidalmarinho.scenes.Scene;
import com.davidalmarinho.utils.Constants;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class SnapToGrid extends Component {
    private final int tileWidth, tileHeight;
    private int x, y;
    private boolean mayPlace;

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

        if (mouseInput.isButtonDown(MouseEvent.BUTTON1)) {
            // Copy the current gameObject to another gameObject, by a properly way to don't make weird bugs
            GameObject gameObject = this.gameObject.copy();
            if (mayPlace) {
                GameManager.getInstance().getCurrentScene().addGameObject(gameObject);
            }
        }
    }

    @Override
    public void render(Graphics2D g2) {
        Camera camera = GameManager.getInstance().getCurrentScene().camera;
        if (mayPlace) {
            g2.setColor(Color.GREEN);
        } else {
            g2.setColor(Color.RED);
        }
        if (!GameManager.getInstance().mouseInput.isDragging) {
            g2.fillRect((int) (x - camera.position.x), (int) (y - camera.position.y),
                    tileWidth, tileHeight);
        }
    }

    @Override
    public Component copy() {
        return null;
    }
}
