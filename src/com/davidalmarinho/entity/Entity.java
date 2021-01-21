package com.davidalmarinho.entity;

import com.davidalmarinho.graphics.RenderHandler;
import com.davidalmarinho.graphics.Window;
import com.davidalmarinho.world.World;

import java.awt.image.BufferedImage;

public abstract class Entity {
    // Attributes
    protected double x, y;
    private final int width, height;
    protected BufferedImage sprite;
    // Animations
    private int index;
    protected int curFps;

    public Entity(double x, double y, int width, int height, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }

    public abstract void tick();

    public abstract void render();

    public void renderLight(RenderHandler.Light light) {}

    protected void updateCamera(int width, int height) {
        int WIDTH = Window.WIDTH;
        int HEIGHT = Window.HEIGHT;
        int tileSize = World.tileSize;

        Camera.x = Camera.clamp(this.getX() + width / 2 - (WIDTH / 2), 0,
                World.width * tileSize - WIDTH);
        Camera.y = Camera.clamp(this.getY() + height / 2 - (HEIGHT / 2), 0,
                World.height * tileSize - HEIGHT);
    }

    protected void animations(int maxFps, int maxIndex) {
        curFps++;
        if (curFps == maxFps) {
            curFps = 0;
            index++;
            if (index == maxIndex) {
                index = 0;
            }
        }
    }

    protected boolean isCollidingPerfect(Entity colliderEntity, Entity receiveCollision) {
        // Entity that receives the collision
        int widthReceiveCollisionEntity = receiveCollision.sprite.getWidth();
        int heightReceiveCollisionEntity = receiveCollision.sprite.getHeight();

        // Entity that will collide
        int widthColliderEntity = colliderEntity.sprite.getWidth();
        int heightColliderEntity = colliderEntity.sprite.getHeight();

        // Save pixels from sprites
        int[] rgbReceiveCollision = new int[widthReceiveCollisionEntity * heightReceiveCollisionEntity];
        receiveCollision.sprite.getRGB(0, 0, widthReceiveCollisionEntity, heightReceiveCollisionEntity, rgbReceiveCollision,
                0, widthReceiveCollisionEntity);

        int[] rgbColliderEntity = new int[widthColliderEntity * heightColliderEntity];
        colliderEntity.sprite.getRGB(0, 0, widthColliderEntity, heightColliderEntity, rgbColliderEntity,
                0, widthColliderEntity);

        for (int xReceiveCollisionSprite = 0; xReceiveCollisionSprite < widthReceiveCollisionEntity; xReceiveCollisionSprite++)
            for (int yReceiveCollisionSprite = 0; yReceiveCollisionSprite < heightReceiveCollisionEntity; yReceiveCollisionSprite++)
                for (int xColliderSprite = 0; xColliderSprite < widthColliderEntity; xColliderSprite++)
                    for (int yColliderSprite = 0; yColliderSprite < heightColliderEntity; yColliderSprite++) {
                        if (rgbReceiveCollision[xReceiveCollisionSprite + yReceiveCollisionSprite * widthReceiveCollisionEntity] == 0xFFff00ff
                                || rgbColliderEntity[xColliderSprite + yColliderSprite * widthColliderEntity] == 0xFFff00ff) {
                            continue;
                        }

                        if ((xReceiveCollisionSprite + receiveCollision.getX() == xColliderSprite + colliderEntity.getX())
                                && (yReceiveCollisionSprite + receiveCollision.getY() == yColliderSprite + colliderEntity.getY())) {
                            return true;
                        }
                    }
        return false;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
