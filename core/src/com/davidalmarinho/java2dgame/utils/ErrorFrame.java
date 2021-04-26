package com.davidalmarinho.java2dgame.utils;

import javax.swing.JOptionPane;

public class ErrorFrame {
    public ErrorFrame(String error) {
        JOptionPane popUp = new JOptionPane();
        JOptionPane.showMessageDialog(null, error,
                "Error", JOptionPane.ERROR_MESSAGE);
        popUp.setVisible(true);
    }
}
