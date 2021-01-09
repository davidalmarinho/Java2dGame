package com.davidalmarinho.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
    private BufferedImage spritesheet;

    public SpriteSheet(String path) {
        try {
            spritesheet = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getSprite(int x, int y, int width, int height) {
        if (x < 0 || y < 0 || x + width >= spritesheet.getWidth() || y + height >= spritesheet.getHeight()) {
            System.out.println("That seems that you are trying to pick a nonexistent position from the spritesheet");
            x = y = 0;
        }
        return spritesheet.getSubimage(x, y, width, height);
    }
}
