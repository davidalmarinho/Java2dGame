package com.davidalmarinho.game_objects.components;

import com.davidalmarinho.input.MouseInput;
import com.davidalmarinho.main.GameManager;
import com.davidalmarinho.utils.Constants;

import java.awt.Color;
import java.awt.Graphics2D;

public class SnapToGrid extends Component {
    private final int width, height;
    private int x, y;

    public SnapToGrid() {
        this.width = Constants.TILE_SIZE;
        this.height = Constants.TILE_SIZE;
    }

    @Override
    public void update(float dt) {
        MouseInput mouseInput = GameManager.getInstance().mouseInput;

        int xMouse = (int) (mouseInput.mousePosition.x);
        int yMouse = (int) (mouseInput.mousePosition.y);

        x = xMouse / 64 * 64;
        y = yMouse / 64 * 64;
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.GREEN);
        g2.fillRect(x, y, Constants.TILE_SIZE, Constants.TILE_SIZE);
    }
}
