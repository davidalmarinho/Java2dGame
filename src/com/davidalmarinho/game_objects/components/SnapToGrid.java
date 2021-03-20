package com.davidalmarinho.game_objects.components;

import com.davidalmarinho.input.MouseInput;
import com.davidalmarinho.main.Camera;
import com.davidalmarinho.main.GameManager;

import java.awt.Color;
import java.awt.Graphics2D;

public class SnapToGrid extends Component {
    private final int tileWidth, tileHeight;
    private int x, y;

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
