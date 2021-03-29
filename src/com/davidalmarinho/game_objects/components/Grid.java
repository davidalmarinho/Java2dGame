package com.davidalmarinho.game_objects.components;

import com.davidalmarinho.main.Camera;
import com.davidalmarinho.main.GameManager;
import com.davidalmarinho.utils.Constants;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Grid extends Component {
    private final int width, height;

    public Grid() {
        this.width = Constants.TILE_SIZE;
        this.height = Constants.TILE_SIZE;
    }

    @Override
    public void render(Graphics2D g2) {
        Camera camera = GameManager.getInstance().getCurrentScene().camera;
        float thickness = 1f;
        g2.setStroke(new BasicStroke(thickness));
        g2.setColor(new Color(0.2f, 0.2f, 0.2f, 0.5f));
        int numOfColumns = (Constants.WORLD_WIDTH) / width;
        int numOfRows = (Constants.WORLD_HEIGHT) / height;

        for (int currentColumn = 0; currentColumn < numOfColumns; currentColumn++) {
            g2.draw(new Line2D.Float(currentColumn * Constants.TILE_SIZE - camera.position.x, 0,
                    currentColumn * Constants.TILE_SIZE - camera.position.x,
                    Constants.WINDOW_HEIGHT + currentColumn * Constants.TILE_SIZE));
        }

        for (int currentRow = 0; currentRow < numOfRows; currentRow++) {
            g2.draw(new Line2D.Float(0, currentRow * Constants.TILE_SIZE - camera.position.y,
                    Constants.WINDOW_WIDTH + currentRow * Constants.TILE_SIZE,
                    currentRow * Constants.TILE_SIZE - camera.position.y));
        }
        g2.setStroke(new BasicStroke());
    }

    public Component copy() {
        return null;
    }
}
