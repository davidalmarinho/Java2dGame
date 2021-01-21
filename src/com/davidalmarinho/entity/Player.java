package com.davidalmarinho.entity;

import com.davidalmarinho.Game;
import com.davidalmarinho.Input;
import com.davidalmarinho.graphics.Color;
import com.davidalmarinho.graphics.RenderHandler;
import com.davidalmarinho.graphics.Screen;
import com.davidalmarinho.world.World;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    private int change = 255;
    private boolean contUp;

    private BufferedImage[] right;
    private BufferedImage[] left;
    private BufferedImage[] up;
    private BufferedImage[] down;
    private boolean walking;
    private char currentDirection = 'r';

    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
        this.loadAnimations();
    }

    private void loadAnimations() {
        int size = World.tileSize;

        right = new BufferedImage[3];
        for (int i = 0; i < right.length; i++) {
            right[i] = Game.spriteSheet.getSprite(0, i * size, size, size);
        }

        left = new BufferedImage[3];
        for (int i = 0; i < left.length; i++) {
            left[i] = Game.spriteSheet.getSprite(0, (3 + i) * size, size, size);
        }

        up = new BufferedImage[3];
        for (int i = 0; i < up.length; i++) {
            up[i] = Game.spriteSheet.getSprite(size, (3 + i) * size, size, size);
        }

        down = new BufferedImage[3];
        for (int i = 0; i < down.length; i++) {
            down[i] = Game.spriteSheet.getSprite(size, i * size, size, size);
        }
    }

    @Override
    public void tick() {
        this.move();
        updateCamera(getWidth(), getHeight());
        if (walking) {
            animations(7, 3);
        } else {
            this.resetAnimation();
        }

        for (int i = 0; i < Game.entities.size(); i++) {
            Entity entity = Game.entities.get(i);
            if (entity instanceof Enemy) {
                if (isCollidingPerfect(this, entity)) {
                    System.out.println("Colliding :)");
                }
            }
        }

        this.changeColourTest();
    }

    private void move() {
        int speed = 1;

        for (int i = speed; i != 0; i--) {
            boolean goRight = (Input.isKey(KeyEvent.VK_RIGHT) || Input.isKey(KeyEvent.VK_D))
                    && World.isFree(getX() + i, getY(), getWidth(), getHeight());
            boolean goLeft = (Input.isKey(KeyEvent.VK_LEFT) || Input.isKey(KeyEvent.VK_A))
                    && World.isFree(getX() - i, getY(), getWidth(), getHeight());
            boolean goUp = (Input.isKey(KeyEvent.VK_UP) || Input.isKey(KeyEvent.VK_W))
                    && World.isFree(getX(), getY() - i, getWidth(), getHeight());
            boolean goDown = (Input.isKey(KeyEvent.VK_DOWN) || Input.isKey(KeyEvent.VK_S))
                    && World.isFree(getX(), getY() + i, getWidth(), getHeight());

            if (goRight) {
                x += i;
                currentDirection = 'r';
                sprite = right[getIndex()];
            } else if (goLeft) {
                x -= i;
                currentDirection = 'l';
                sprite = left[getIndex()];
            }

            if (goUp) {
                y -= i;
                currentDirection = 'u';
                sprite = up[getIndex()];
            } else if (goDown) {
                y += i;
                currentDirection = 'd';
                sprite = down[getIndex()];
            }

            walking = goRight | goLeft | goUp | goDown;
        }
    }

    private void resetAnimation() {
        if (!walking) {
            if (currentDirection == 'r') {
                sprite = right[0];
            } else if (currentDirection == 'l') {
                sprite = left[0];
            }

            if (currentDirection == 'd') {
                sprite = down[0];
            } else if (currentDirection == 'u') {
                sprite = up[0];
            }
        }
    }

    private void changeColourTest() {
        if (change == 255) {
            contUp = false;
        } else if (change == 0) {
            contUp = true;
        }

        if (contUp) {
            change++;
        } else {
            change--;
        }
    }

    @Override
    public void render() {
        RenderHandler.ARGB argb = Screen.screen.getRenderHandler().getARGB();
        RenderHandler.RGB rgb = Screen.screen.getRenderHandler().getRGB();

        int x = getX() - Camera.x;
        int y = getY() - Camera.y;

        if (currentDirection == 'r')
            rgb.drawSprite(right[getIndex()], x, y);
        else if (currentDirection == 'l')
            rgb.drawSprite(left[getIndex()], x, y);


        if (currentDirection == 'u')
            rgb.drawSprite(up[getIndex()], x, y);
        else if (currentDirection == 'd')
            rgb.drawSprite(down[getIndex()], x, y);

        //rgb.drawSprite(sprite, x, y);

        // RECTANGLES:
        // rgb.drawRectangleRGB(x, y, getWidth(), getHeight(), new Color(255, 255, 255));
        argb.drawRectangleARGB(x, y, getWidth(), getHeight(), new Color(0, 0, 255, 140));
        // argb.drawRectangleARGB(x, y, 16, 16, Color.get(0, 0, 255, chng));

        // OVALS:
        // rgb.drawOvalRGB(x, y, 30, getHeight(), Color.get(0, 0 ,chng));
        // argb.drawOvalARGB(x, y, getWidth(), getHeight(), Color.get(50, chng, 255, chng));
    }

    public void renderLight(RenderHandler.Light light) {
        int width = 60;// 60;
        int height = 60;// 60;

        int x = this.getX() + getWidth() / 2 - width / 2 - Camera.x;
        int y = this.getY() + getHeight() / 2 - height / 2 - Camera.y;
        light.renderCircleLight(x, y,
                width, height, new Color(200, 255, 255, 255), 6);
    }
}
