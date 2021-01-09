package com.davidalmarinho.world;

import com.davidalmarinho.Game;
import com.davidalmarinho.entity.Camera;
import com.davidalmarinho.graphics.RenderHandler;

import java.awt.image.BufferedImage;

public class Tile {
    protected int x, y;
    BufferedImage image;

    public Tile(int x, int y, BufferedImage tileSprite) {
        this.x = x;
        this.y = y;
        this.image = tileSprite;
    }

    public void render(Game game) {
        RenderHandler.RGB rgb = game.getRenderHandler().getRGB();

        rgb.drawSprite(image, x - Camera.x, y - Camera.y);
        //g.drawImage(image, this.x - Camera.x, this.y - Camera.y, null);
    }

    public static class TilesLib {
        public static BufferedImage floorTile = Game.spriteSheet.getSprite(32, 0, 16, 16);
        public static BufferedImage wallTile = Game.spriteSheet.getSprite(48, 0, 16, 16);
    }
}
