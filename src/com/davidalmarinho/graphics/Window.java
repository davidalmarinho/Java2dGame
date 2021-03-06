package com.davidalmarinho.graphics;

import com.davidalmarinho.utils.Constants;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private static Window window;

    /* Here we will apply a structure named as Singleton.
     * Following this structure, we create an instance of a class and we turn
     * private our constructor.
     * But, why we will use this?
     * Very simple. We just want a window for our game, we don't want the mistake
     * that we create many windows and just crash the software of our pc.
     * So, when we want to "create" a new Window, we write:
     * Window window = Window.getInstance();
     * By this way, we will reuse always the same window.
     * We also can access to Windows's variables and methods by an easier way using this.
     * -> Ex.: Window.get().dispose();
     */
    private Window() {
        this.setTitle("2D Platform Game");
        this.setResizable(true);
        this.setMinimumSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        this.pack();
        this.setSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static Window getInstance() {
        if (window == null) {
            window = new Window();
        }

        return window;
    }
}
