package com.davidalmarinho.game_objects.components;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {
    public BufferedImage image;

    public Sprite(String path) {
        File file = new File(path);
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Sprite(BufferedImage image) {
        this.image = image;
    }
}
