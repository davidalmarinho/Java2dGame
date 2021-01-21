package com.davidalmarinho.world;

import com.davidalmarinho.Game;
import com.davidalmarinho.entity.Camera;
import com.davidalmarinho.entity.Enemy;
import com.davidalmarinho.graphics.Window;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class World {
    private static Tile[] tiles;
    public static int width, height;
    public static int tileSize = 16;
    private int exponent;

    public World(Game game, int curLevel) {
        try {
            for (int i = 0; true; i++) {
                if (Math.pow(2, i) == tileSize) {
                    exponent = i;
                    break;
                }

                // Check bug
                if (i == tileSize) {
                    System.out.println("That tileSize isn't available, please try 16, 32, 64, 128 or 256");
                    System.exit(2);
                    break;
                }
            }

            BufferedImage worldPlan = ImageIO.read(getClass().getResource("/level" + curLevel + ".png"));
            width = worldPlan.getWidth();
            height = worldPlan.getHeight();

            int[] pixels = new int[width * height];
            worldPlan.getRGB(0, 0, width, height,
                    pixels, 0, width);

            tiles = new Tile[width * height];

            for (int curX = 0; curX < width; curX++)
                for (int curY = 0; curY < height; curY++) {
                    tiles[curX + curY * width] = new FloorTile(curX * tileSize, curY * tileSize,
                            Tile.TilesLib.floorTile);

                    int curPixel = pixels[curX + curY * width];
                    setTile(tiles, curPixel, curX, curY);
                    if (curPixel == 0xFFff0000) {
                        Enemy enemy = new Enemy(curX * tileSize, curY * tileSize,
                                tileSize, tileSize, Game.spriteSheet.getSprite(0, 0, 1, 1));
                        Game.entities.add(enemy);
                    }
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setTile(Tile[] tiles, int curPixel, int curX, int curY) {
        if (curPixel == 0xFFffffff) {
            tiles[curX + curY * width] = new FloorTile(curX * tileSize, curY * tileSize,
                    Tile.TilesLib.floorTile);
        } else if (curPixel == 0xFF000000) {
            tiles[curX + curY * width] = new SolidTile(curX * tileSize, curY * tileSize,
                    Tile.TilesLib.wallTile);
        }
    }

    public static boolean isFree(int xNext, int yNext, int width, int height) {
        int startX = xNext / tileSize;
        int startY = yNext / tileSize;

        int finalX = (xNext - 1 + width) / tileSize;
        int finalY = (yNext - 1 + height) / tileSize;

        for (int x = startX; x <= finalX; x++) {
            for (int y = startY; y <= finalY; y++) {
                if (tiles[x + y * World.width] instanceof SolidTile)
                    return false;
            }
        }
        return true;
    }

    public void render() {
        int startX, startY;
        startX = Camera.x >> exponent;
        startY = Camera.y >> exponent;

        int finalX, finalY;
        finalX = startX + (Window.WIDTH >> 4);
        finalY = startY + (Window.HEIGHT >> 4);

        for (int xx = startX; xx <= finalX + 1; xx++)
            for (int yy = startY; yy <= finalY + 1; yy++) {
                if (xx < 0 || xx >= width || yy < 0 || yy >= height) continue;

                Tile tile = tiles[xx + yy * width];
                tile.render();
            }
    }
}
