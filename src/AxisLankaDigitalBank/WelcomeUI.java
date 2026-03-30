package AxisLankaDigitalBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WelcomeUI extends JFrame {

    public WelcomeUI() {
        setTitle("Axis Lanka Digital Bank");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true); 

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, Theme.getWelcomeStart(), 0, getHeight(), Theme.getWelcomeEnd());
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // --- NEW: MODERN CIRCULAR CLOSE BUTTON ---
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20)); // Generous padding from the corner
        topBar.setOpaque(false);
        topBar.setMaximumSize(new Dimension(400, 60));
        topBar.add(createCircularCloseButton());
        mainPanel.add(topBar);

        mainPanel.add(Box.createVerticalStrut(120)); 
        
        JLabel title = new JLabel("Axis Lanka");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 36));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subtitle = new JLabel("Digital Bank");
        subtitle.setForeground(new Color(0, 120, 255)); 
        subtitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel tagline = new JLabel("Smart Banking for a Smart Future");
        tagline.setForeground(new Color(200, 200, 200));
        tagline.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tagline.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(title);
        mainPanel.add(subtitle);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(tagline);
        mainPanel.add(Box.createVerticalGlue()); 

        JButton startBtn = createRoundedButton("Get Started", new Color(0, 102, 255), Color.WHITE);
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        startBtn.setMaximumSize(new Dimension(320, 50)); 
        
        startBtn.addActionListener(e -> {
            new LoginUI().setVisible(true);
            this.dispose();
        });

        mainPanel.add(startBtn);
        mainPanel.add(Box.createVerticalStrut(50));

        add(mainPanel);
    }

    // --- DRAWING THE CUSTOM CIRCULAR BUTTON ---
    private JButton createCircularCloseButton() {
        JButton btn = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw the circular background (Translucent frosted look)
                if (getModel().isRollover()) {
                    g2.setColor(new Color(255, 255, 255, 80)); // Brighter on hover
                } else {
                    g2.setColor(new Color(255, 255, 255, 40)); // Soft translucent
                }
                g2.fillOval(0, 0, getWidth(), getHeight());

                // Draw the perfectly centered 'X'
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); // Rounded line edges
                
                int pad = 10; // Padding defines how large the X is inside the circle
                // Draw \
                g2.drawLine(pad, pad, getWidth() - pad, getHeight() - pad);
                // Draw /
                g2.drawLine(getWidth() - pad, pad, pad, getHeight() - pad);

                g2.dispose();
            }
        };
        
        btn.setPreferredSize(new Dimension(32, 32));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> System.exit(0)); // Closes the app
        
        return btn;
    }

    public static JButton createThemeToggle(JFrame frame, Runnable onToggle) {
        JButton toggle = new JButton(Theme.isDarkMode ? "Light Mode" : "Dark Mode");
        toggle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        toggle.setForeground(Theme.getTextSecondary());
        toggle.setContentAreaFilled(false);
        toggle.setBorderPainted(false);
        toggle.setFocusPainted(false);
        toggle.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        toggle.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { toggle.setForeground(Theme.getTextPrimary()); }
            public void mouseExited(MouseEvent e) { toggle.setForeground(Theme.getTextSecondary()); }
        });

        toggle.addActionListener(e -> {
            Theme.isDarkMode = !Theme.isDarkMode;
            frame.dispose(); 
            onToggle.run();  
        });
        return toggle;
    }

    public static JButton createRoundedButton(String text, Color bgColor, Color fgColor) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25); 
                super.paintComponent(g);
                g2.dispose();
            }
        };
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setBackground(bgColor);
        btn.setForeground(fgColor);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(bgColor.darker()); }
            public void mouseExited(MouseEvent e) { btn.setBackground(bgColor); }
        });
        return btn;
    }
}