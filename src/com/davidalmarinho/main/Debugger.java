package com.davidalmarinho.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Debugger {
    public static Debugger instance;
    private int fps;
    public boolean debugging;
    private int countGameObjects;

    private Debugger() {
        debugging = false;
    }

    public void update(float dt) {
        if (!debugging) return;

        fps = (int) (1.0 / dt);

        countGameObjects();
    }

    public void draw(Graphics g) {
        if (!debugging) return;

        g.setColor(new Color(125, 125, 125));
        g.setFont(new Font("arial", Font.BOLD, 16));
        g.drawString("FPS: " + fps, 16, 32);
        g.drawString("GO: " + countGameObjects, 16, 64);
    }

    private void countGameObjects() {
        countGameObjects = 0;
        for (int i = 0; i < GameManager.getInstance().getCurrentScene().gameObjects.size(); i++) {
            countGameObjects = i;
        }
    }

    // We want just a Debugger
    public static Debugger getInstance() {
        if (instance == null) {
            instance = new Debugger();
        }

        return instance;
    }
}
