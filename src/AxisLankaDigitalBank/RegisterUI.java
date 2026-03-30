package AxisLankaDigitalBank;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterUI extends JFrame {

    public RegisterUI() {
        setTitle("Sign Up");
        setSize(400, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Theme.getPanelBg());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Theme.getPanelBg());
        mainPanel.setBorder(new EmptyBorder(10, 0, 30, 0));

        // --- NEW: TOP BAR WITH BACK BUTTON & THEME TOGGLE ---
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Theme.getPanelBg());
        topBar.setMaximumSize(new Dimension(360, 40)); 
        topBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton backBtn = new JButton("← Back");
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setForeground(Theme.getTextSecondary());
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);
        backBtn.setFocusPainted(false);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { backBtn.setForeground(Theme.getTextPrimary()); }
            public void mouseExited(MouseEvent e) { backBtn.setForeground(Theme.getTextSecondary()); }
        });
        backBtn.addActionListener(e -> {
            new WelcomeUI().setVisible(true);
            this.dispose();
        });

        topBar.add(backBtn, BorderLayout.WEST);
        topBar.add(WelcomeUI.createThemeToggle(this, () -> new RegisterUI().setVisible(true)), BorderLayout.EAST);
        mainPanel.add(topBar);

        JLabel title = new JLabel("Create Account");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(Theme.getTextPrimary());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(30));

        JTextField nameField = LoginUI.createStyledTextField();
        JTextField emailField = LoginUI.createStyledTextField();
        JTextField nicField = LoginUI.createStyledTextField();
        JTextField mobileField = LoginUI.createStyledTextField();
        
        JPasswordField passField = new JPasswordField(); 
        JPasswordField rePassField = new JPasswordField();

        mainPanel.add(LoginUI.createInputWrapper("Full Name", nameField));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(LoginUI.createInputWrapper("Email Address", emailField));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(LoginUI.createInputWrapper("NIC Number", nicField));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(LoginUI.createInputWrapper("Mobile Number", mobileField));
        mainPanel.add(Box.createVerticalStrut(15));
        
        mainPanel.add(LoginUI.createPasswordWrapper("Password", passField));
        
        JPanel instructionContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        instructionContainer.setBackground(Theme.getPanelBg());
        instructionContainer.setMaximumSize(new Dimension(320, 20)); 
        instructionContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel passInstruction = new JLabel("Must be 8+ characters with numbers & symbols.");
        passInstruction.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        passInstruction.setForeground(Theme.getTextSecondary());
        instructionContainer.add(passInstruction);
        mainPanel.add(instructionContainer);

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(LoginUI.createPasswordWrapper("Confirm Password", rePassField));
        mainPanel.add(Box.createVerticalStrut(30));

        JButton registerBtn = WelcomeUI.createRoundedButton("Sign Up", new Color(0, 102, 255), Color.WHITE);
        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerBtn.setMaximumSize(new Dimension(320, 50)); 
        mainPanel.add(registerBtn);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.setBackground(Theme.getPanelBg());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footer.setBackground(Theme.getPanelBg());
        footer.setBorder(new EmptyBorder(10, 0, 20, 0));
        JLabel haveAcc = new JLabel("Already have an account? ");
        haveAcc.setForeground(Theme.getTextSecondary());
        JLabel loginLink = new JLabel("Login");
        loginLink.setForeground(new Color(0, 102, 255));
        loginLink.setFont(new Font("Segoe UI", Font.BOLD, 12));
        loginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        footer.add(haveAcc);
        footer.add(loginLink);
        add(footer, BorderLayout.SOUTH);

        registerBtn.addActionListener(e -> {
            String pass = new String(passField.getPassword());
            String rePass = new String(rePassField.getPassword());

            if (!pass.equals(rePass)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match!");
                return;
            }

            boolean success = BankDatabase.getInstance().registerUser(
                nameField.getText(), emailField.getText(), nicField.getText(), mobileField.getText(), pass
            );

            if (success) {
                JOptionPane.showMessageDialog(this, "Account Created!");
                new LoginUI().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Email already exists.");
            }
        });

        loginLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new LoginUI().setVisible(true);
                dispose();
            }
        });
    }
}