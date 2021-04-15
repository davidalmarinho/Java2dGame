package com.davidalmarinho.graphics;

import com.davidalmarinho.input.MouseInput;
import com.davidalmarinho.utils.Constants;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;

public class Window extends Canvas {
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
    private Window(MouseInput mouseInput) {
        addMouseMotionListener(mouseInput);
        JFrame jFrame = new JFrame("Zombie Apocalypse");
        // Add Canvas to our JFrame
        jFrame.add(this);
        jFrame.setResizable(true);
        jFrame.pack();
        jFrame.setMinimumSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        // It will prioritize the Game's window when created
    }

    public static Window getInstance(MouseInput mouseInput) {
        if (window == null) {
            window = new Window(mouseInput);
        }

        return window;
    }
}
