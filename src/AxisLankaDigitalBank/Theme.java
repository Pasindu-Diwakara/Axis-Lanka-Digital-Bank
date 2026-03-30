package AxisLankaDigitalBank;

import java.awt.Color;

public class Theme {
    public static boolean isDarkMode = false;

    // --- App Backgrounds ---
    public static Color getBg() {
        return isDarkMode ? new Color(18, 20, 29) : new Color(248, 250, 252);
    }
    public static Color getPanelBg() {
        return isDarkMode ? new Color(30, 33, 45) : Color.WHITE;
    }

    // --- Text Colors ---
    public static Color getTextPrimary() {
        return isDarkMode ? Color.WHITE : new Color(30, 30, 30);
    }
    public static Color getTextSecondary() {
        return isDarkMode ? new Color(150, 155, 170) : new Color(130, 130, 130);
    }

    // --- Input Fields (Login & Register) ---
    public static Color getInputBg() {
        return isDarkMode ? new Color(40, 44, 60) : new Color(250, 250, 250);
    }
    public static Color getInputBorder() {
        return isDarkMode ? new Color(60, 65, 85) : new Color(220, 220, 220);
    }
    public static Color getInputText() {
        return isDarkMode ? Color.WHITE : Color.BLACK;
    }

    // --- Welcome Screen Gradient ---
    public static Color getWelcomeStart() {
        return isDarkMode ? new Color(5, 10, 25) : new Color(10, 25, 60);
    }
    public static Color getWelcomeEnd() {
        return isDarkMode ? new Color(2, 5, 10) : new Color(5, 15, 35);
    }

    // --- Dashboard Specific ---
    public static Color getCardStart() {
        return isDarkMode ? new Color(10, 50, 200) : new Color(0, 80, 255);
    }
    public static Color getCardEnd() {
        return isDarkMode ? new Color(5, 100, 220) : new Color(0, 150, 255);
    }

    // --- PREMIUM TEXT ACTION BUTTONS ---
    // Subtle background tints
    public static Color getActionBgDeposit() { return isDarkMode ? new Color(20, 40, 70) : new Color(235, 245, 255); }
    public static Color getActionBgWithdraw() { return isDarkMode ? new Color(70, 25, 25) : new Color(255, 240, 240); }
    public static Color getActionBgTransfer() { return isDarkMode ? new Color(20, 60, 30) : new Color(240, 255, 240); }
    public static Color getActionBgBills() { return isDarkMode ? new Color(70, 50, 15) : new Color(255, 250, 235); }

    // Bold, highly legible text colors
    public static Color getActionTextDeposit() { return isDarkMode ? new Color(120, 190, 255) : new Color(0, 102, 204); }
    public static Color getActionTextWithdraw() { return isDarkMode ? new Color(255, 120, 120) : new Color(204, 0, 0); }
    public static Color getActionTextTransfer() { return isDarkMode ? new Color(120, 230, 140) : new Color(0, 153, 76); }
    public static Color getActionTextBills() { return isDarkMode ? new Color(255, 190, 100) : new Color(204, 102, 0); }
}