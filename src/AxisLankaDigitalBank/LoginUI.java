package AxisLankaDigitalBank;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginUI extends JFrame {

    public LoginUI() {
        setTitle("Login");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Theme.getPanelBg());
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Theme.getPanelBg());
        mainPanel.setBorder(new EmptyBorder(10, 0, 50, 0)); 

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
        topBar.add(WelcomeUI.createThemeToggle(this, () -> new LoginUI().setVisible(true)), BorderLayout.EAST);
        mainPanel.add(topBar);

        JLabel welcomeLabel = new JLabel("Welcome Back!");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcomeLabel.setForeground(Theme.getTextPrimary());
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subLabel = new JLabel("Login to your account");
        subLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subLabel.setForeground(Theme.getTextSecondary());
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(welcomeLabel);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(subLabel);
        mainPanel.add(Box.createVerticalStrut(40));

        JTextField emailField = createStyledTextField();
        JPasswordField passField = new JPasswordField();

        mainPanel.add(createInputWrapper("Email Address", emailField));
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(createPasswordWrapper("Password", passField));
        
        JPanel fpPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        fpPanel.setBackground(Theme.getPanelBg());
        fpPanel.setMaximumSize(new Dimension(320, 30));
        fpPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel forgotPass = new JLabel("Forgot Password?");
        forgotPass.setForeground(new Color(0, 102, 255));
        forgotPass.setFont(new Font("Segoe UI", Font.BOLD, 12));
        forgotPass.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fpPanel.add(forgotPass);
        
        mainPanel.add(fpPanel);
        mainPanel.add(Box.createVerticalStrut(30));

        JButton loginBtn = WelcomeUI.createRoundedButton("Login", new Color(0, 102, 255), Color.WHITE);
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.setMaximumSize(new Dimension(320, 50));
        mainPanel.add(loginBtn);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footer.setBackground(Theme.getPanelBg());
        footer.setBorder(new EmptyBorder(0, 0, 30, 0));
        JLabel noAcc = new JLabel("Don't have an account? ");
        noAcc.setForeground(Theme.getTextSecondary());
        JLabel signUp = new JLabel("Sign Up");
        signUp.setForeground(new Color(0, 102, 255));
        signUp.setFont(new Font("Segoe UI", Font.BOLD, 12));
        signUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        footer.add(noAcc);
        footer.add(signUp);
        add(footer, BorderLayout.SOUTH);

        loginBtn.addActionListener(e -> {
            String email = emailField.getText();
            String pass = new String(passField.getPassword());
            Account acc = BankDatabase.getInstance().login(email, pass);
            if (acc != null) {
                new DashboardUI(acc).setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
            }
        });

        signUp.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new RegisterUI().setVisible(true);
                dispose();
            }
        });
    }

    public static JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Theme.getInputBorder(), 1, true),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBackground(Theme.getInputBg());
        field.setForeground(Theme.getInputText());
        field.setCaretColor(Theme.getInputText());
        return field;
    }

    public static JPanel createInputWrapper(String labelText, JComponent inputField) {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(Theme.getPanelBg());
        wrapper.setAlignmentX(Component.CENTER_ALIGNMENT); 
        wrapper.setMaximumSize(new Dimension(320, 70));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(Theme.getTextSecondary());
        label.setAlignmentX(Component.LEFT_ALIGNMENT); 

        inputField.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputField.setMaximumSize(new Dimension(320, 45));

        wrapper.add(label);
        wrapper.add(Box.createVerticalStrut(5));
        wrapper.add(inputField);
        return wrapper;
    }

    public static JPanel createPasswordWrapper(String labelText, JPasswordField passField) {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(Theme.getPanelBg());
        wrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
        wrapper.setMaximumSize(new Dimension(320, 70));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(Theme.getTextSecondary());
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel innerContainer = new JPanel(new BorderLayout());
        innerContainer.setBackground(Theme.getInputBg());
        innerContainer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Theme.getInputBorder(), 1, true),
            BorderFactory.createEmptyBorder(5, 15, 5, 5) 
        ));
        innerContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        innerContainer.setMaximumSize(new Dimension(320, 45));

        passField.setBorder(null);
        passField.setBackground(Theme.getInputBg());
        passField.setForeground(Theme.getInputText());
        passField.setCaretColor(Theme.getInputText());
        passField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passField.setEchoChar('•'); 

        JButton toggleBtn = new JButton("Show");
        toggleBtn.setFont(new Font("Segoe UI", Font.BOLD, 11));
        toggleBtn.setForeground(Theme.getTextSecondary());
        toggleBtn.setContentAreaFilled(false);
        toggleBtn.setBorderPainted(false);
        toggleBtn.setFocusPainted(false);
        toggleBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        toggleBtn.addActionListener(e -> {
            if (toggleBtn.getText().equals("Show")) {
                passField.setEchoChar((char) 0); 
                toggleBtn.setText("Hide");
            } else {
                passField.setEchoChar('•'); 
                toggleBtn.setText("Show");
            }
        });

        innerContainer.add(passField, BorderLayout.CENTER);
        innerContainer.add(toggleBtn, BorderLayout.EAST);

        wrapper.add(label);
        wrapper.add(Box.createVerticalStrut(5));
        wrapper.add(innerContainer);
        return wrapper;
    }
}