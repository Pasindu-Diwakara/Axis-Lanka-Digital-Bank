package AxisLankaDigitalBank;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashboardUI extends JFrame {
    private Account account;
    private JLabel balanceLabel;
    private JPanel historyContainer;

    public DashboardUI(Account account) {
        this.account = account;
        setTitle("Dashboard");
        setSize(400, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Theme.getBg()); 
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Theme.getBg());
        mainPanel.setBorder(new EmptyBorder(10, 0, 25, 0)); 

        // --- NEW: TOP BAR WITH LOGOUT & THEME TOGGLE ---
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Theme.getBg());
        topBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        topBar.setMaximumSize(new Dimension(340, 40)); 
        
        JButton logoutBtn = new JButton("Log Out");
        logoutBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        logoutBtn.setForeground(Theme.getTextSecondary());
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Premium hover effect (turns red)
        logoutBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { logoutBtn.setForeground(new Color(255, 80, 80)); }
            public void mouseExited(MouseEvent e) { logoutBtn.setForeground(Theme.getTextSecondary()); }
        });
        
        logoutBtn.addActionListener(e -> {
            // Logs out and securely returns to the Login screen
            new LoginUI().setVisible(true);
            this.dispose();
        });

        topBar.add(logoutBtn, BorderLayout.WEST);
        topBar.add(WelcomeUI.createThemeToggle(this, () -> new DashboardUI(this.account).setVisible(true)), BorderLayout.EAST);
        mainPanel.add(topBar);
        mainPanel.add(Box.createVerticalStrut(10));

        // 1. Header with Greeting
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Theme.getBg());
        headerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.setMaximumSize(new Dimension(340, 50)); 
        
        JLabel greeting = new JLabel("<html><span style='color:gray;font-size:10px;'>Welcome back,</span><br/><b>" + account.getFullName() + "</b></html>");
        greeting.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        greeting.setForeground(Theme.getTextPrimary());
        headerPanel.add(greeting, BorderLayout.WEST);

        // 2. The Blue Balance Card 
        JPanel cardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, Theme.getCardStart(), getWidth(), getHeight(), Theme.getCardEnd());
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setOpaque(false);
        cardPanel.setBorder(new EmptyBorder(20, 25, 20, 25));
        cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.setMaximumSize(new Dimension(340, 160)); 

        JLabel balTitle = new JLabel("Total Balance");
        balTitle.setForeground(new Color(255, 255, 255, 200));
        balTitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        balanceLabel = new JLabel("Rs. " + String.format("%,.2f", account.getBalance()));
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        
        JLabel accNum = new JLabel("Acc: " + account.getAccountNumber());
        accNum.setForeground(Color.WHITE);
        accNum.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        cardPanel.add(balTitle);
        cardPanel.add(Box.createVerticalStrut(10));
        cardPanel.add(balanceLabel);
        cardPanel.add(Box.createVerticalStrut(20));
        cardPanel.add(accNum);

        // 3. Quick Actions Grid (Premium Text Pills)
        JPanel actionPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        actionPanel.setBackground(Theme.getBg());
        actionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        actionPanel.setMaximumSize(new Dimension(340, 45)); 

        JPanel btnDeposit = createPremiumTextButton("Deposit", Theme.getActionBgDeposit(), Theme.getActionTextDeposit());
        JPanel btnWithdraw = createPremiumTextButton("Withdraw", Theme.getActionBgWithdraw(), Theme.getActionTextWithdraw());
        JPanel btnTransfer = createPremiumTextButton("Transfer", Theme.getActionBgTransfer(), Theme.getActionTextTransfer());
        JPanel btnBills = createPremiumTextButton("Bills", Theme.getActionBgBills(), Theme.getActionTextBills());

        actionPanel.add(btnDeposit);
        actionPanel.add(btnWithdraw);
        actionPanel.add(btnTransfer);
        actionPanel.add(btnBills);

        // 4. Transactions List
        JPanel historyHeader = new JPanel(new BorderLayout());
        historyHeader.setBackground(Theme.getBg());
        historyHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        historyHeader.setMaximumSize(new Dimension(340, 30));

        JLabel historyTitle = new JLabel("Recent Transactions");
        historyTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        historyTitle.setForeground(Theme.getTextPrimary());
        historyHeader.add(historyTitle, BorderLayout.WEST);
        
        historyContainer = new JPanel();
        historyContainer.setLayout(new BoxLayout(historyContainer, BoxLayout.Y_AXIS));
        historyContainer.setBackground(Theme.getBg());

        JScrollPane scrollPane = new JScrollPane(historyContainer);
        scrollPane.setBorder(null);
        scrollPane.setBackground(Theme.getBg());

        mainPanel.add(headerPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(cardPanel);
        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(actionPanel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(historyHeader);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(scrollPane);

        add(mainPanel, BorderLayout.CENTER);

        // Actions Logic
        btnDeposit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String val = JOptionPane.showInputDialog("Enter deposit amount:");
                try {
                    account.deposit(Double.parseDouble(val), "Cash Deposit");
                    updateUI();
                } catch (Exception ex) {}
            }
        });

        btnTransfer.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                showTransferDialog();
            }
        });

        updateUI();
    }

    private void showTransferDialog() {
        JDialog transferDialog = new JDialog(this, "Send Money", true);
        transferDialog.setSize(350, 450);
        transferDialog.setLocationRelativeTo(this);
        transferDialog.setLayout(new BoxLayout(transferDialog.getContentPane(), BoxLayout.Y_AXIS));
        transferDialog.getContentPane().setBackground(Theme.getPanelBg());
        
        JPanel pad = new JPanel();
        pad.setLayout(new BoxLayout(pad, BoxLayout.Y_AXIS));
        pad.setBackground(Theme.getPanelBg());
        pad.setBorder(new EmptyBorder(20, 0, 20, 0));

        JLabel title = new JLabel("Send Money");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Theme.getTextPrimary());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        pad.add(title);
        pad.add(Box.createVerticalStrut(20));

        JTextField emailField = LoginUI.createStyledTextField();
        JTextField amtField = LoginUI.createStyledTextField();
        
        pad.add(LoginUI.createInputWrapper("Recipient Email", emailField));
        pad.add(Box.createVerticalStrut(15));
        pad.add(LoginUI.createInputWrapper("Amount (Rs.)", amtField));
        pad.add(Box.createVerticalStrut(30));

        JButton sendBtn = WelcomeUI.createRoundedButton("Send Now", new Color(0, 102, 255), Color.WHITE);
        sendBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        sendBtn.setMaximumSize(new Dimension(320, 50));
        
        sendBtn.addActionListener(ev -> {
            try {
                double amt = Double.parseDouble(amtField.getText());
                Account target = BankDatabase.getInstance().getAccountByEmail(emailField.getText());
                if (target != null && account.withdraw(amt, "Transfer to " + target.getFullName())) {
                    target.deposit(amt, "Transfer from " + account.getFullName());
                    updateUI();
                    transferDialog.dispose();
                    JOptionPane.showMessageDialog(this, "Transfer Successful!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed. Check balance/email.");
                }
            } catch (Exception ex) {}
        });

        pad.add(sendBtn);
        transferDialog.add(pad);
        transferDialog.setVisible(true);
    }

    private void updateUI() {
        balanceLabel.setText("Rs. " + String.format("%,.2f", account.getBalance()));
        historyContainer.removeAll();
        for (int i = account.getHistory().size() - 1; i >= 0; i--) {
            JPanel row = createTxRow(account.getHistory().get(i));
            row.setAlignmentX(Component.CENTER_ALIGNMENT);
            historyContainer.add(row);
            historyContainer.add(Box.createVerticalStrut(10));
        }
        historyContainer.revalidate();
        historyContainer.repaint();
    }

    private JPanel createTxRow(Transaction t) {
        JPanel row = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Theme.getPanelBg()); 
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            }
        };
        row.setOpaque(false);
        row.setBorder(new EmptyBorder(15, 15, 15, 15));
        row.setMaximumSize(new Dimension(340, 65));

        JLabel title = new JLabel(t.getDescription());
        title.setFont(new Font("Segoe UI", Font.BOLD, 14));
        title.setForeground(Theme.getTextPrimary()); 
        
        JLabel date = new JLabel(t.getDate());
        date.setForeground(Theme.getTextSecondary());
        date.setFont(new Font("Segoe UI", Font.PLAIN, 11));

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setOpaque(false);
        left.add(title);
        left.add(date);

        JLabel amt = new JLabel("Rs. " + String.format("%,.2f", t.getAmount()));
        amt.setFont(new Font("Segoe UI", Font.BOLD, 14));
        if (t.getType().equals("Deposit") || t.getDescription().startsWith("Transfer from")) {
            amt.setForeground(new Color(0, 180, 80)); 
        } else {
            amt.setForeground(Theme.getTextPrimary()); 
        }

        row.add(left, BorderLayout.WEST);
        row.add(amt, BorderLayout.EAST);
        return row;
    }

    private JPanel createPremiumTextButton(String text, Color bgColor, Color fgColor) {
        JPanel container = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bgColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
            }
        };
        container.setOpaque(false);
        container.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(fgColor);
        container.add(label);

        return container;
    }
}