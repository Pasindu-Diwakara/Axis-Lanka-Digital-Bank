package AxisLankaDigitalBank;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Use system look and feel as a base
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new WelcomeUI().setVisible(true);
        });
    }
}