package com.davidalmarinho.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInput extends KeyAdapter {
    // Just a keyboard
    private static KeyboardInput instance;
    private final int numberOfKeys = 256;
    private final boolean[] keys = new boolean[numberOfKeys];
    private final boolean[] lastKeys = new boolean[numberOfKeys];

    private KeyboardInput() {

    }

    public void update() {
        // Update lastKeys boolean
        for (int i = 0; i < lastKeys.length; i++) {
            lastKeys[i] = keys[i];
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    // Always doing action while pressing
    public boolean isKey(int keyCode) {
        if (keyCode > numberOfKeys) {
            return false;
        }
        return keys[keyCode];
    }

    // Do just an action when we press it
    public boolean isKeyDown(int keyCode) {
        /*if (keyCode > numberOfKeys) {
            return false;
        }*/
        return keys[keyCode] && !lastKeys[keyCode];
    }

    // Do an action when we release it
    public boolean isKeyUp(int keyCode) {
        if (keyCode > numberOfKeys) {
            return false;
        }
        return !keys[keyCode] && lastKeys[keyCode];
    }

    public static KeyboardInput getInstance() {
        if (instance == null) {
            instance = new KeyboardInput();
        }

        return instance;
    }
}
