package com.davidalmarinho.entity.player;

import com.davidalmarinho.Input;
import com.davidalmarinho.graphics.Window;
import com.davidalmarinho.world.World;

public class Cursor {
    private double x, y;
    private int xGrid, yGrid;
    public boolean checking = true;

    public void tickCoordinates() {
        x = (Input.x / Window.xRealScale);
        y = (Input.y / Window.yRealScale);

        makeGrid();
    }

    private void makeGrid() {
        xGrid = (int) x / World.tileSize * World.tileSize;
        yGrid = (int) y / World.tileSize * World.tileSize;
    }

    public int getyGrid() {
        return yGrid;
    }

    public int getxGrid() {
        return xGrid;
    }
}
