package com.davidalmarinho.graphics;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {
    private final int SCALE = 3;
    private final int WIDTH = 640 / SCALE;
    private final int HEIGHT = 480 / SCALE;
    private int widthWindow = WIDTH;
    private int heightWindow = HEIGHT;

    public Window() {
        createWindow();
    }

    private void createWindow() {
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        JFrame jFrame = new JFrame("018_PixelManipulation");
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

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWidthWindow() {
        return widthWindow;
    }

    public int getHeightWindow() {
        return heightWindow;
    }

    public int getSCALE() {
        return SCALE;
    }

    public Canvas getCanvas() {
        return this;
    }
}
