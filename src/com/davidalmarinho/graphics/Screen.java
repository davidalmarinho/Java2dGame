package com.davidalmarinho.graphics;

import com.davidalmarinho.Game;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Screen extends RenderHandler {
    public static Screen screen;

    public Screen() {
        super();
    }

    public void render() {
        BufferStrategy bs = Window.window.getBufferStrategy();
        int WIDTH = Window.window.widthWindow;
        int HEIGHT = Window.window.heightWindow;
        int SCALE = Window.SCALE;

        if (bs == null) {
            Window.window.getCanvas().createBufferStrategy(2);
            return;
        }

        Graphics g = getRenderHandler().getRGB().rgbGameBackground.getGraphics();
        
        // g.setColor(new Color(255, 0, 0));
        // g.fillRect(0, 0, 16, 16);

        Game.world.render();
        this.renderErrorMessage(g);

        g = bs.getDrawGraphics();
        this.renderEntities();
        this.renderLight();
        g.drawImage(getRGB().rgbGameBackground, 0, 0,
                WIDTH * SCALE,
                HEIGHT * SCALE, null);
        g.drawImage(getARGB().argbGameBackground, 0, 0, WIDTH * SCALE,
                HEIGHT * SCALE, null);
        g.drawImage(getLight().lightBackground, 0, 0, WIDTH * SCALE,
                HEIGHT * SCALE, null);
        g.dispose();
        bs.show();
        getRGB().clear(getRGB().rgbPixels);
        getARGB().clear();
        getLight().clear();
    }

    private void renderEntities() {
        for (int i = 0; i < Game.entities.size(); i++) {
            Game.entities.get(i).render();
        }
    }

    private void renderLight() {
        getLight().renderDarkness(new Color(0, 0, 0, 210));
        for (int i = 0; i < Game.entities.size(); i++) {
            // Game.entities.get(i).renderLight(getLight());
        }
    }

    private void renderErrorMessage(Graphics g) {
        if (Game.errorOccurred) {
            g.setColor(new java.awt.Color(255, 0, 0));
            g.setFont(new Font("arial", Font.BOLD, 16));
            g.drawString("Error", 8, 16);
        }
    }

    public static Screen get() {
        if (screen == null) screen = new Screen();

        return screen;
    }

    public RenderHandler getRenderHandler() {
        return this;
    }
}
