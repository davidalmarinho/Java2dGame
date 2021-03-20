package com.davidalmarinho.game_objects.components;

import com.davidalmarinho.input.MouseInput;
import com.davidalmarinho.main.Camera;
import com.davidalmarinho.main.GameManager;
import com.davidalmarinho.utils.Constants;
import com.davidalmarinho.utils.Vector2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class CameraControllers extends Component {
    private final Vector2 velocity;

    public CameraControllers() {
        velocity = new Vector2(100.0f, 100.0f);
    }

    @Override
    public void update(float dt) {
        // Move with cursor
        Camera camera = GameManager.getInstance().getCurrentScene().camera;
        MouseInput mouseInput = GameManager.getInstance().mouseInput;

        // Move Left
        if (mouseInput.mousePosition.x <= Constants.TILE_SIZE) {
            camera.position.x -= dt * velocity.x;
        }

        // Move Down
        if (mouseInput.mousePosition.y >= Constants.WINDOW_HEIGHT - Constants.TILE_SIZE) {
            camera.position.y += dt * velocity.y;
        }

        // Move Up
        if (mouseInput.mousePosition.y <= Constants.TILE_SIZE) {
            camera.position.y -= dt * velocity.y;
        }

        // Move Right
        if (mouseInput.mousePosition.x >= Constants.WINDOW_WIDTH - Constants.TILE_SIZE) {
            camera.position.x += dt * velocity.x;
        }

        // Don't move to left if there is no more World in left
        int minWidth = 0;
        if (camera.position.x <= minWidth) {
            camera.position.x = minWidth;
        }

        // Don't move to up if there is no more World in up
        int minHeight = 0;
        if (camera.position.y < minHeight) {
            camera.position.y = 0;
        }

        // Don't move to right if there is no more World in right
        int maxWidth = Constants.WORLD_WIDTH - Constants.WINDOW_WIDTH;
        if (camera.position.x >= maxWidth && !GameManager.getInstance().getKeyboardInput().isKey(KeyEvent.VK_RIGHT)) {
            camera.position.x = maxWidth;
        }

        // Don't move to down if there is no more World in down
        int maxHeight = Constants.WORLD_HEIGHT - Constants.WINDOW_HEIGHT;
        if (camera.position.y >= maxHeight && !GameManager.getInstance().getKeyboardInput().isKey(KeyEvent.VK_DOWN)) {
            camera.position.y = maxHeight;
        }
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(new Color(255));
        // Up
        g2.fillRect(100, 0, Constants.WINDOW_WIDTH - 200, Constants.TILE_SIZE);
        // Right
        g2.fillRect(Constants.WINDOW_WIDTH - Constants.TILE_SIZE, 100, Constants.TILE_SIZE, Constants.WINDOW_HEIGHT - 200);
        // Down
        g2.fillRect(100, Constants.WINDOW_HEIGHT - Constants.TILE_SIZE, Constants.WINDOW_WIDTH - 200, Constants.TILE_SIZE);
        // Left
        g2.fillRect(0, 100, Constants.TILE_SIZE, Constants.WINDOW_HEIGHT - 200);
    }
}
