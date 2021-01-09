package com.davidalmarinho;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {
    private final boolean[] keys = new boolean[255];
    private final boolean[] lastKeys = new boolean[255];

    public Input(Game game) {
        game.getWindow().addKeyListener(this);
    }

    public void tick() {
        for (int i = 0; i < keys.length; i++) {
            lastKeys[i] = keys[i];
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public boolean isKeyDown(int keyCode) {
        return keys[keyCode] && !lastKeys[keyCode];
    }

    public boolean isKeyUp(int keyCode) {
        return !keys[keyCode] && lastKeys[keyCode];
    }

    public boolean isKey(int keyCode) {
        return keys[keyCode];
    }
}
