/**
 * CheckingAccount.java
 * Subclass of BankAccount with interest rate and overdraft allowance.
 */
public class CheckingAccount extends BankAccount {
    private double interestRate; // e.g., 0.03 for 3%
    public static final double OVERDRAFT_FEE = 30.00;

    /** Default constructor */
    public CheckingAccount() {
        super();
    }

    /**
     * Constructor with account holder information and interest rate.
     * @param firstName Account holder's first name
     * @param lastName Account holder's last name
     * @param accountID Unique account ID
     * @param interestRate Annual interest rate (e.g., 0.025 for 2.5%)
     */
    public CheckingAccount(String firstName, String lastName, int accountID, double interestRate) {
        super(firstName, lastName, accountID);
        this.interestRate = interestRate;
    }

    /** @return Current interest rate */
    public double getInterestRate() { return interestRate; }

    /** @param interestRate Sets the annual interest rate */
    public void setInterestRate(double interestRate) { this.interestRate = interestRate; }

    /**
     * Processes a withdrawal, allowing overdraft if necessary.
     * @param amount Amount to withdraw (must be > 0)
     * @return Status message indicating the transaction result
     */
    public String processWithdrawal(double amount) {
        try {
            if (amount <= 0.0) {
                throw new IllegalArgumentException("Withdrawal amount must be positive.");
            }
            double available = getBalance();
            if (amount <= available) {
                return super.withdrawal(amount);
            } else {
                double newBalance = available - amount - OVERDRAFT_FEE;
                double delta = newBalance - available;
                adjustBalance(delta);
                return String.format(
                    "[OVERDRAFT] Withdrew $%.2f. $%.2f overdraft fee assessed. New balance: $%.2f (fee accessed).",
                    amount, OVERDRAFT_FEE, getBalance()
                );
            }
        } catch (Exception e) {
            return "Overdraft withdrawal failed: " + e.getMessage();
        }
    }

    /**
     * Displays the account summary and interest rate.
     */
    public void displayAccount() {
        accountSummary();
        System.out.printf("Interest Rate  : %.2f%%%n", interestRate * 100.0);
    }
}
