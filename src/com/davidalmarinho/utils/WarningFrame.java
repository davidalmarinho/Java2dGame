package com.davidalmarinho.utils;

import javax.swing.*;

public class WarningFrame {
    public WarningFrame(String warning) {
        JOptionPane popUp = new JOptionPane();
        JOptionPane.showMessageDialog(null, warning);
        popUp.setVisible(true);
    }
}
