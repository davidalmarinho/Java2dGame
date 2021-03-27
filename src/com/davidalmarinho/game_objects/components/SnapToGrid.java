package com.davidalmarinho.game_objects.components;

import com.davidalmarinho.game_objects.GameObject;
import com.davidalmarinho.input.MouseInput;
import com.davidalmarinho.main.Camera;
import com.davidalmarinho.main.GameManager;
import com.davidalmarinho.scenes.LevelEditorScene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class SnapToGrid extends Component {
    private final int tileWidth, tileHeight;
    private int x, y;
    int c = 0;

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

        if (mouseInput.isButtonDown(MouseEvent.BUTTON1)) {
            // Update positions
            this.gameObject.transform.position.x = x;
            this.gameObject.transform.position.y = y;

            // Copy the current gameObject to another gameObject, by a properly way to don't make weird bugs
            GameObject gameObject = this.gameObject.copy();
            // TODO: 27/03/2021 Simplify, upgrade this
            gameObject.addComponent(((LevelEditorScene) GameManager.getInstance().getCurrentScene()).editorSpritesheet.sprites.get(7).copy());
            GameManager.getInstance().getCurrentScene().addGameObject(gameObject);
        }
    }

    @Override
    public void render(Graphics2D g2) {
        Camera camera = GameManager.getInstance().getCurrentScene().camera;
        g2.setColor(Color.GREEN);
        if (!GameManager.getInstance().mouseInput.isDragging) {
            g2.fillRect((int) (x - camera.position.x), (int) (y - camera.position.y),
                    tileWidth, tileHeight);
        }
    }
}
