package com.davidalmarinho.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Window extends Canvas {
    public static Window window;
    private JFrame jFrame;
    public static int SCALE = 4;
    public static int WIDTH = 640 / SCALE;
    public static int HEIGHT = 480 / SCALE;
    public int widthWindow = WIDTH;
    public int heightWindow = HEIGHT;
    public static double xRealScale = 4;
    public static double yRealScale = 4;

    public Window() {
        createWindow();
    }

    private void createWindow() {
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        jFrame = new JFrame("2d_Game");
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

        // Update the scale according with the Window
        BufferedImage gameImg = Screen.screen.getRGB().rgbGameBackground;
        int widthJFrame = jFrame.getContentPane().getWidth();
        int heightJFrame = jFrame.getContentPane().getHeight();
        xRealScale = (float) widthJFrame / gameImg.getWidth();
        yRealScale = (float) heightJFrame / gameImg.getHeight();
    }

    public static Window get() {
        if (window == null) window = new Window();

        return window;
    }

    public Canvas getCanvas() {
        return this;
    }
}
