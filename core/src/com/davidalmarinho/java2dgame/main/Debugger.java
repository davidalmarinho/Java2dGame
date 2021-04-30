package com.davidalmarinho.java2dgame.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

// TODO: 30/04/2021 Needs to be restored
public class Debugger {
    public static Debugger instance;
    private int fps;
    private float timer;
    public boolean debugging;
    private int countGameObjects;

    private Debugger() {
        debugging = false;
    }

    public void update(float dt) {
        if (!debugging) return;
        timer += dt;

        if (timer >= 0.03) {
            fps = (int) (1.0 / dt);
            timer = .0f;
        }

        countGameObjects();
        System.out.println(countGameObjects);
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
        while (countGameObjects < GameManager.getInstance().getCurrentScene().gameObjects.size()) {
            countGameObjects++;
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
