/**
 * DealershipDemo.java
 * Test driver for the car dealership financing scenario.
 */
public class DealershipDemo {
    /**
     * Main entry point for the program.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        CheckingAccount customer = new CheckingAccount("Richard", "Aquilo", 42017, 0.025);
        System.out.println("Welcome to Apex Auto Group Financing.");
        customer.displayAccount();
        System.out.println();

        System.out.println("== Down Payment ==");
        System.out.println(customer.deposit(1500.00));
        customer.displayAccount();
        System.out.println();

        System.out.println("== Documentation Fee ==");
        System.out.println(customer.withdrawal(175.00));
        customer.displayAccount();
        System.out.println();

        System.out.println("== Add-ons Bundle (warranty + service plan + accessories) ==");
        System.out.println(customer.processWithdrawal(1400.00));
        customer.displayAccount();
        System.out.println();

        System.out.println("== Attempt additional withdrawal while negative ==");
        System.out.println(customer.withdrawal(10.00));
        customer.displayAccount();
        System.out.println();

        System.out.println("== End-of-Day Account Summary ==");
        customer.accountSummary();
        System.out.printf("Applied interest rate (informational): %.2f%%%n", customer.getInterestRate() * 100);
    }
}
