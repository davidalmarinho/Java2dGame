package com.davidalmarinho;

import com.davidalmarinho.graphics.Window;

import java.awt.event.*;

public class Input implements KeyListener, MouseListener, MouseMotionListener {
    // Keyboard
    private static final int numberOfKeys = 256;
    private static final boolean[] keys = new boolean[numberOfKeys];
    private static final boolean[] lastKeys = new boolean[numberOfKeys];

    // Mouse
    private static final int numberOfButtons = 5;
    private static final boolean[] buttons = new boolean[numberOfButtons];
    private static final boolean[] lastButtons = new boolean[numberOfButtons];
    public static double x, y;

    public Input() {
        Window window = Window.window;
        window.addKeyListener(this);
        window.addMouseListener(this);
        window.addMouseMotionListener(this);
    }

    public void tick() {
        /* We are going to update the lastKeys and the lastButtons arrays
         * with a delay to have a better controller in the user's inputs.
         */
        for (int i = 0; i < keys.length; i++) {
            lastKeys[i] = keys[i];
        }
        for (int i = 0; i < buttons.length; i++) {
            lastButtons[i] = buttons[i];
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

    // Do action when we press the key
    public static boolean isKeyDown(int keyCode) {
        return keys[keyCode] && !lastKeys[keyCode];
    }

    // Do action when we realise the key
    public static boolean isKeyUp(int keyCode) {
        return !keys[keyCode] && lastKeys[keyCode];
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // Do action while we are holding the key
    public static boolean isKey(int keyCode) {
        return keys[keyCode];
    }

    // Do action when we press the button
    public static boolean isButtonDown(int button) {
        return buttons[button] && !lastButtons[button];
    }

    // Do action when we realise the button
    public static boolean isButtonUp(int button) {
        return !buttons[button] && lastButtons[button];
    }

    // Do action when we are holding the button
    public static boolean isButton(int button) {
        return buttons[button];
    }
}
