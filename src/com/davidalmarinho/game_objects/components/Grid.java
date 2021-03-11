package com.davidalmarinho.game_objects.components;

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
        int numOfColumns = Constants.WINDOW_WIDTH / width;
        int numOfRows = Constants.WINDOW_HEIGHT / height;
        for (int currentColumn = 0; currentColumn <= numOfColumns; currentColumn++) {
            for (int currentRow = 0; currentRow <= numOfRows; currentRow++) {
                g2.setColor(new Color(0, 0, 0));
                g2.drawRect(currentColumn * width, currentRow * height, width, height);
            }
        }
    }
}
