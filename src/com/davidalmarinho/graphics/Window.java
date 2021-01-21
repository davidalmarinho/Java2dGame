package com.davidalmarinho.graphics;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {
    public static Window window;
    public static int SCALE = 3;
    public static int WIDTH = 640 / SCALE;
    public static int HEIGHT = 480 / SCALE;
    public int widthWindow = WIDTH;
    public int heightWindow = HEIGHT;

    public Window() {
        createWindow();
    }

    private void createWindow() {
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        JFrame jFrame = new JFrame("2d_Game");
        jFrame.add(this);
        jFrame.setResizable(true);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    public void tick() {
        this.widthWindow = (int) Math.ceil((float) getWidth() / SCALE);
        this.heightWindow = (int) Math.ceil((float) getHeight() / SCALE);
    }

    public static Window get() {
        if (window == null) window = new Window();

        return window;
    }

    public Canvas getCanvas() {
        return this;
    }
}
