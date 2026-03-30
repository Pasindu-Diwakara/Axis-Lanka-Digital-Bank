package AxisLankaDigitalBank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String type; 
    private double amount;
    private String date;
    private String description;

    public Transaction(String type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd, yyyy");  
        this.date = dtf.format(LocalDateTime.now());
    }

    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }
    public String getDescription() { return description; }

    public String getTransactionDetails() {
        return date + " | " + type + " | LKR " + amount + " | " + description;
    }
}