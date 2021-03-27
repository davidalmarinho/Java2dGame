package com.davidalmarinho.game_objects.components;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite extends Component {
    public BufferedImage image;
    private int width, height;

    public Sprite(String path) {
        File file = new File(path);
        try {
            image = ImageIO.read(file);
            this.width = image.getWidth();
            this.height = image.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Sprite(BufferedImage image) {
        this.image = image;
    }

    public Component copy() {
        return new Sprite(this.image);
    }

    @Override
    public void render(Graphics2D g2) {
        drawQuick(g2, this);
    }
}
