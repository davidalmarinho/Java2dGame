package com.davidalmarinho.entity;

import com.davidalmarinho.Game;
import com.davidalmarinho.entity.player.Camera;
import com.davidalmarinho.graphics.RenderHandler;
import com.davidalmarinho.graphics.Screen;
import com.davidalmarinho.world.World;

import java.awt.image.BufferedImage;

public class Enemy extends Entity {
    private final BufferedImage[] right;
    private final BufferedImage[] left;

    public Enemy(double x, double y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
        int size = World.tileSize;

        right = new BufferedImage[3];
        for (int i = 0; i < right.length; i++) {
            right[i] = Game.spriteSheet.getSprite(2 * size, size + i * size,
                    size, size);
        }

        left = new BufferedImage[3];
        for (int i = 0; i < left.length; i++) {
            left[i] = Game.spriteSheet.getSprite(3 * size, size + i * size,
                    size, size);
        }
    }

    @Override
    public void tick() {
        animations(7, 3);
        double speed = 0.0;
        ai(speed);
    }

    private void ai(double speed) {
        // Animations
        if ((int) x < Game.player.getX() && World.isFree((int) (x + speed), this.getY(),
                this.getWidth(), this.getHeight())) {
            sprite = right[getIndex()];
            x += speed;
        } else if ((int) x > Game.player.getX() && World.isFree((int) (x - speed), this.getY(),
                this.getWidth(), this.getHeight())) {
            sprite = left[getIndex()];
            x -= speed;
        }

        if ((int) y < Game.player.getY() && World.isFree(this.getX(), (int) (y + speed),
                this.getWidth(), this.getHeight())) {
            sprite = right[getIndex()];
            y += speed;
        } else if ((int) y > Game.player.getY() && World.isFree(this.getX(), (int) (y - speed),
                this.getWidth(), this.getHeight())) {
            sprite = left[getIndex()];
            y -= speed;
        }
    }

    @Override
    public void render() {
        RenderHandler.RGB rgb = Screen.screen.getRenderHandler().getRGB();
        rgb.drawSprite(sprite, getX() - Camera.x, getY() - Camera.y);
    }
}
