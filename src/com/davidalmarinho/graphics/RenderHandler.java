package com.davidalmarinho.graphics;

import com.davidalmarinho.Game;

import java.awt.image.*;
import java.util.Arrays;

public class RenderHandler {
    private final RGB rgb;
    private final ARGB argb;
    private final Light light;

    public RenderHandler() {
        int WIDTH = Window.WIDTH;
        int HEIGHT = Window.HEIGHT;

        argb = new ARGB(WIDTH, HEIGHT);
        rgb = new RGB(WIDTH, HEIGHT);
        light = new Light(WIDTH, HEIGHT);
    }

    /**
     * Draw a personalized rectangle
     *
     * @param pixels The pixels that we want to use
     * @param xPos   Entity's X position
     * @param yPos   Entity's Y position
     * @param width  Entity's Width
     * @param height Entity's Height
     * @param color  Rectangle's color
     */
    private void drawRectangle(int[] pixels, int xPos, int yPos, int width, int height, Color color) {
        for (int x = xPos; x < xPos + width; x++) {
            for (int y = yPos; y < yPos + height; y++) {
                setPixel(pixels, color, x, y);
            }
        }
    }

    /**
     * Draw a personalized oval
     *
     * @param pixels The pixels that we want to use
     * @param xPos   Entity's X position
     * @param yPos   Entity's Y position
     * @param width  Entity's Width
     * @param height Entity's Height
     * @param color  Oval's color
     */
    private void drawOval(int[] pixels, int xPos, int yPos, int width, int height, Color color) {
        // Get the centers of the oval
        int centerX = width / 2 + xPos;
        int centerY = height / 2 + yPos;

        int finalX = xPos + width;
        int finalY = yPos + height;

        // Make a square
        for (int xx = xPos; xx < finalX; xx++) {
            for (int yy = yPos; yy < finalY; yy++) {
                //pixels[xx + yy * game.getWindow().getWIDTH()] = 0xFF000000;
                // Get the center point if the oval and check the distance with the square's sides
                int distance = (int) Math.sqrt((xx - centerX) * (xx - centerX)
                        + (yy - centerY) * (yy - centerY));
                if (distance < width / 2 & distance < height / 2) {
                    setPixel(pixels, color, xx, yy);
                }
            }
        }
    }

    private void setPixel(int[] pixels, Color color, int x, int y) {
        int WIDTH = Window.WIDTH;
        int HEIGHT = Window.HEIGHT;

        if (x < 0 || x >= WIDTH) return;
        if (y < 0 || y >= HEIGHT) return;


        pixels[x + (y * WIDTH)] = color.getColor();
    }

    public class RGB {
        protected final BufferedImage rgbGameBackground;
        protected final int[] rgbPixels;

        public RGB(int WIDTH, int HEIGHT) {
            rgbGameBackground = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            rgbPixels = ((DataBufferInt) rgbGameBackground.getRaster().getDataBuffer()).getData();
        }

        public void drawRectangleRGB(int xPos, int yPos, int width, int height, Color color) {
            drawRectangle(rgbPixels, xPos, yPos, width, height, color);
        }

        public void drawOvalRGB(int xPos, int yPos, int width, int height, Color color) {
            drawOval(rgbPixels, xPos, yPos, width, height, color);
        }

        /**
         * Draw RGB sprite
         *
         * @param sprite  Sprite's image
         * @param entityX The X position on the Entity
         * @param entityY The Y position on the Entity
         */
        public void drawSprite(BufferedImage sprite, int entityX, int entityY) {
            int[] rgbArray = sprite.getRGB(0, 0, sprite.getWidth(), sprite.getHeight(),
                    null, 0, sprite.getWidth());
            this.setPallet(rgbArray);

            for (int x = 0; x < sprite.getWidth(); x++) {
                int xPixel = x + entityX;
                if (xPixel < 0 || xPixel >= rgbGameBackground.getWidth()) continue;

                for (int y = 0; y < sprite.getHeight(); y++) {
                    int yPixel = y + entityY;
                    if (yPixel < 0 || yPixel >= rgbGameBackground.getHeight()) continue;

                    setSpritePixel(rgbPixels, rgbGameBackground, xPixel, yPixel, rgbArray[x + y * sprite.getWidth()]);
                }
            }
        }

        private void setSpritePixel(int[] pixels, BufferedImage background, int x, int y, int value) {
            if (value == 0xFFff00ff) return;
            int curPixel = x + y * background.getWidth();

            if (curPixel >= pixels.length) return;

            pixels[curPixel] = value;
        }

        private void setPallet(int[] rgbArray) {
            for (int i = 0; i < rgbArray.length; i++) {
                if (rgbArray[i] == 0xFF0c0c0c) {
                    rgbArray[i] = 0xFFc6c000;
                } else if (rgbArray[i] == 0xFF280000) {
                    rgbArray[i] = 0xFF285d00;
                }
            }
        }

        public void clear(int[] pixels) {
            for (int i = 0; i < pixels.length; i++) {
                pixels[i] = 0xFFff00ff;
            }
        }
    }

    public class ARGB {
        protected final BufferedImage argbGameBackground;
        protected final int[] argbPixels;

        public ARGB(int WIDTH, int HEIGHT) {
            argbGameBackground = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
            argbPixels = ((DataBufferInt) argbGameBackground.getRaster().getDataBuffer()).getData();
        }

        public void drawRectangleARGB(int xPos, int yPos, int width, int height, Color color) {
            drawRectangle(argbPixels, xPos, yPos, width, height, color);
        }

        public void drawOvalARGB(int xPos, int yPos, int width, int height, Color color) {
            drawOval(argbPixels, xPos, yPos, width, height, color);
        }

        public void clear() {
            for (int i = 0; i < argbPixels.length; i++) {
                argbPixels[i] = 0x00000000;
            }
        }
    }

    public class Light {
        protected final BufferedImage lightBackground;
        protected final int[] lightPixels;

        public Light(int WIDTH, int HEIGHT) {
            lightBackground = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
            lightPixels = ((DataBufferInt) lightBackground.getRaster().getDataBuffer()).getData();
        }

        public void renderDarkness(Color color) {
            // Arrays.fill(lightPixels, color.getColor());
        }

        /**
         *
         * @param x X position of the Entity
         * @param y Y position of the Entity
         * @param width Width's Entity
         * @param height Height's Entity
         * @param color The color's light
         * @param scale Turn into a more pixelated light
         */
        public void renderCircleLight(int x, int y, int width, int height, Color color, int scale) {
            // Get the centers of the oval
            int centerX = width / 2 + x;
            int centerY = height / 2 + y;

            int finalX = x + width;
            int finalY = y + height;

            int lightTonality;
            // Make a square
            for (int xx = x; xx < finalX; xx++) {
                for (int yy = y; yy < finalY; yy++) {
                    // Get the center point if the oval and check the distance with the square's sides
                    int currentDistance = (int) Math.sqrt((xx - centerX) * (xx - centerX)
                            + (yy - centerY) * (yy - centerY));
                    int maxDistance = (int) Math.sqrt((x - centerX) * (x - centerX)
                            + (y - centerY) * (y - centerY));
                    if (currentDistance < width / 2 & currentDistance < height / 2) {
                        int startLight = maxDistance * (color.a / maxDistance);
                        int curLight = currentDistance * (color.a / maxDistance);

                        /*if (maxDistance - currentDistance <= 2)
                            System.out.println(maxDistance - currentDistance);*/

                        // Get the color of the third distance (ghost distance)
                        lightTonality = (startLight - curLight);

                        // To turn darker and make a more pixelated  light
                        int darkness = (currentDistance / scale) * (color.a / maxDistance);
                        if (lightTonality > darkness)
                            lightTonality -= darkness;
                        else
                            continue;

                        // Exception
                        if (lightTonality < 0 || lightTonality > 255) {
                            Game.errorOccurred = true;
                            continue;
                        }

                        setPixel(lightPixels, new Color(color.r, color.g, color.b, lightTonality), xx, yy);
                    }
                }
            }
        }

        public void clear() {
            Arrays.fill(lightPixels, 0x00ffffff);
        }
    }

    public RGB getRGB() {
        return rgb;
    }

    public ARGB getARGB() {
        return argb;
    }

    public Light getLight() {
        return light;
    }
}
