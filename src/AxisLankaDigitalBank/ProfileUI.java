package AxisLankaDigitalBank;

import javax.swing.*;
import java.awt.*;

public class ProfileUI extends JFrame {

    public ProfileUI(Account account, DashboardUI dashboard) {
        setTitle("Axis Lanka Digital Bank - My Profile");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 51, 102));
        headerPanel.setPreferredSize(new Dimension(400, 60));
        JLabel title = new JLabel("Account Profile");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        headerPanel.add(Box.createVerticalStrut(30));
        headerPanel.add(title);
        add(headerPanel, BorderLayout.NORTH);

        JPanel detailsPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        detailsPanel.add(createDetailLabel("Full Name: ", account.getFullName()));
        detailsPanel.add(createDetailLabel("Account Number: ", account.getAccountNumber()));
        detailsPanel.add(createDetailLabel("Email: ", account.getEmail()));
        detailsPanel.add(createDetailLabel("NIC Number: ", account.getNic()));
        detailsPanel.add(createDetailLabel("Mobile Number: ", account.getMobile()));

        add(detailsPanel, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        JButton backBtn = new JButton("Back to Dashboard");
        backBtn.setBackground(new Color(0, 153, 76));
        backBtn.setForeground(Color.WHITE);
        footerPanel.add(backBtn);
        add(footerPanel, BorderLayout.SOUTH);

        backBtn.addActionListener(e -> {
            dashboard.setVisible(true);
            this.dispose();
        });
    }

    private JLabel createDetailLabel(String labelText, String value) {
        JLabel label = new JLabel("<html><b>" + labelText + "</b> " + value + "</html>");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return label;
    }
}