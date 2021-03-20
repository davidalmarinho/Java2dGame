package com.davidalmarinho.input;

import com.davidalmarinho.utils.Constants;
import com.davidalmarinho.utils.Vector2;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
    private static MouseInput instance;
    // Keep coordinates
    public Vector2 mousePosition, mouseDragPosition;
    // Controllers
    private final int numberOfButtons = 5;
    private final boolean[] buttons = new boolean[numberOfButtons];
    private final boolean[] lastButtons = new boolean[numberOfButtons];
    public boolean isDragging;

    private MouseInput() {
        mousePosition = new Vector2();
        mouseDragPosition = new Vector2();
    }

    public void update() {
        for (int i = 0; i < numberOfButtons; i++) {
            lastButtons[i] = buttons[i];
        }
    }

    public boolean isButton(int button) {
        return buttons[button];
    }

    public boolean isButtonDown(int button) {
        return buttons[button] && !lastButtons[button];
    }

    public boolean isButtonUp(int button) {
        return !buttons[button] && lastButtons[button];
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        buttons[mouseEvent.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        buttons[mouseEvent.getButton()] = false;
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        isDragging = false;
        mousePosition.x = mouseEvent.getX() / Constants.WINDOW_SCALE_X;
        mousePosition.y = mouseEvent.getY() / Constants.WINDOW_SCALE_Y;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        isDragging = true;
        mouseDragPosition.x = mouseEvent.getX() / Constants.WINDOW_SCALE_X - mousePosition.x;
        mouseDragPosition.y = mouseEvent.getY() / Constants.WINDOW_SCALE_Y - mousePosition.y;
    }

    public static MouseInput getInstance() {
        if (instance == null) {
            instance = new MouseInput();
        }

        return instance;
    }
}
