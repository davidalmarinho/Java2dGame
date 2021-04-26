package com.davidalmarinho.java2dgame.utils;

import javax.swing.*;

public class WarningFrame {
    public WarningFrame(String warning) {
        JOptionPane popUp = new JOptionPane();
        JOptionPane.showMessageDialog(null, warning, "Warning", JOptionPane.WARNING_MESSAGE);
        popUp.setVisible(true);
    }
}
