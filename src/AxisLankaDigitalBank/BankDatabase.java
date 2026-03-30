package AxisLankaDigitalBank;

import java.util.HashMap;
import java.util.Map;

public class BankDatabase {
    private static BankDatabase instance;
    private Map<String, Account> accounts;

    private BankDatabase() {
        accounts = new HashMap<>();
        // Dummy account for testing
        Account testAcc = new Account("Test User", "test@axis.lk", "200012345678", "0771234567", "1234");
        testAcc.deposit(5000.0, "Initial Branch Deposit");
        accounts.put("test@axis.lk", testAcc);
    }

    public static BankDatabase getInstance() {
        if (instance == null) {
            instance = new BankDatabase();
        }
        return instance;
    }

    public boolean registerUser(String name, String email, String nic, String mobile, String password) {
        if (accounts.containsKey(email)) {
            return false;
        }
        accounts.put(email, new Account(name, email, nic, mobile, password));
        return true;
    }

    public Account login(String email, String password) {
        Account acc = accounts.get(email);
        if (acc != null && acc.checkPassword(password)) {
            return acc;
        }
        return null;
    }

    public Account getAccountByEmail(String email) {
        return accounts.get(email);
    }
}