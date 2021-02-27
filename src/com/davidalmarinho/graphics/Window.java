package com.davidalmarinho.graphics;

import com.davidalmarinho.utils.Constants;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private static Window window;

    public Window() {
        this.setTitle("2D Open World Game");
        this.setSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static Window get() {
        if (window == null) {
            window = new Window();
        }

        return window;
    }
}
