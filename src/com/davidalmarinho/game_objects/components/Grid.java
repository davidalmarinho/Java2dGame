package com.davidalmarinho.game_objects.components;

import com.davidalmarinho.main.Camera;
import com.davidalmarinho.main.GameManager;
import com.davidalmarinho.utils.Constants;

import java.awt.Color;
import java.awt.Graphics2D;

public class Grid extends Component {
    private final int width, height;

    public Grid() {
        this.width = Constants.TILE_SIZE;
        this.height = Constants.TILE_SIZE;
    }

    @Override
    public void render(Graphics2D g2) {
        Camera camera = GameManager.getInstance().getCurrentScene().camera;

        int numOfColumns = (Constants.WORLD_WIDTH) / width;
        int numOfRows = (Constants.WORLD_HEIGHT) / height;
        for (int currentColumn = 0; currentColumn < numOfColumns; currentColumn++) {
            for (int currentRow = 0; currentRow < numOfRows; currentRow++) {
                g2.setColor(new Color(0, 0, 0));
                g2.drawRect((int) (currentColumn * width - camera.position.x),
                        (int) (currentRow * height - camera.position.y), width, height);
            }
        }
    }

    public Component copy() {
        return null;
    }
}
