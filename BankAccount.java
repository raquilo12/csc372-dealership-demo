/**
 * BankAccount.java
 * Superclass for a simple banking model used by a car dealership scenario.
 */
public class BankAccount {
    private String firstName;
    private String lastName;
    private int accountID;
    private double balance;

    /**
     * No-arg constructor: starts accounts at zero balance.
     */
    public BankAccount() {
        this.balance = 0.0;
    }

    /**
     * Convenience constructor for initializing account holder information.
     * @param firstName Account holder's first name
     * @param lastName  Account holder's last name
     * @param accountID Unique numeric account identifier
     */
    public BankAccount(String firstName, String lastName, int accountID) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountID = accountID;
    }

    /**
     * Deposits funds into the account. Rejects non-positive amounts.
     * @param amount Amount to deposit (must be > 0)
     * @return Status message indicating success or reason for failure
     */
    public String deposit(double amount) {
        try {
            requirePositive(amount, "Deposit amount must be positive.");
            balance += amount;
            return String.format("[DEPOSIT] $%.2f -> Balance: $%.2f", amount, balance);
        } catch (Exception e) {
            return "Deposit failed: " + e.getMessage();
        }
    }

    /**
     * Withdraws funds from the account without overdraft support.
     * @param amount Amount to withdraw (must be > 0 and <= current balance)
     * @return Status message indicating success or reason for failure
     */
    public String withdrawal(double amount) {
        try {
            requirePositive(amount, "Withdrawal amount must be positive.");
            if (amount > balance) {
                throw new IllegalArgumentException("Insufficient funds.");
            }
            balance -= amount;
            return String.format("[WITHDRAWAL] $%.2f -> Balance: $%.2f", amount, balance);
        } catch (Exception e) {
            return "Withdrawal failed: " + e.getMessage();
        }
    }

    /** @return Account holder's first name */
    public String getFirstName() { return firstName; }

    /** @return Account holder's last name */
    public String getLastName()  { return lastName; }

    /** @return Account's unique numeric ID */
    public int getAccountID()    { return accountID; }

    /** @return Current account balance */
    public double getBalance()   { return balance; }

    /** @param firstName Sets the account holder's first name */
    public void setFirstName(String firstName) { this.firstName = firstName; }

    /** @param lastName Sets the account holder's last name */
    public void setLastName(String lastName)   { this.lastName = lastName; }

    /** @param accountID Sets the account's unique numeric ID */
    public void setAccountID(int accountID)    { this.accountID = accountID; }

    /**
     * Protected hook for subclasses to adjust the balance directly.
     * Intended for cases such as overdrafts.
     * @param delta Amount to add to the current balance (can be negative)
     */
    protected void adjustBalance(double delta) {
        this.balance += delta;
    }

    /**
     * Prints a friendly summary of all account information.
     */
    public void accountSummary() {
        System.out.println("=== Account Summary ===");
        System.out.println("Account Holder : " + firstName + " " + lastName);
        System.out.println("Account ID     : " + accountID);
        System.out.printf ("Current Balance: $%.2f%n", balance);
    }

    /**
     * Utility method to ensure a positive numeric input.
     * @param amount Value to check
     * @param message Error message for invalid input
     */
    private void requirePositive(double amount, String message) {
        if (amount <= 0.0) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Returns a string representation of the BankAccount.
     * @return Human-readable account info string
     */
    @Override
    public String toString() {
        return String.format("BankAccount{%s %s, id=%d, balance=$%.2f}",
                firstName, lastName, accountID, balance);
    }
}
