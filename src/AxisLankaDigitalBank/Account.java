package AxisLankaDigitalBank;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String fullName;
    private String email;
    private String nic;
    private String mobile;
    private String password;
    private double balance;
    private String accountNumber;
    private List<Transaction> transactionHistory; // Composition: Account HAS-A List of Transactions

    public Account(String fullName, String email, String nic, String mobile, String password) {
        this.fullName = fullName;
        this.email = email;
        this.nic = nic;
        this.mobile = mobile;
        this.password = password;
        this.balance = 0.0; // Real banks start with a 0 balance until a deposit is made
        this.accountNumber = String.format("%08d", (int)(Math.random() * 100000000));
        this.transactionHistory = new ArrayList<>();
    }

    // Getters
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getNic() { return nic; }
    public String getMobile() { return mobile; }
    public double getBalance() { return balance; }
    public String getAccountNumber() { return accountNumber; }
    public List<Transaction> getHistory() { return transactionHistory; }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public void deposit(double amount, String description) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount, description));
        }
    }

    public boolean withdraw(double amount, String description) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount, description));
            return true;
        }
        return false;
    }
    
    public void addBillPayment(double amount, String billerName) {
        if (withdraw(amount, "Paid to: " + billerName)) {
            // Transaction is already recorded by the withdraw method
        }
    }
}