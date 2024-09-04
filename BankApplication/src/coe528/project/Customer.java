package coe528.project;

public class Customer extends User {
    private Account account;
    private State customerLevel;

    public Customer(String username, String password) {
        super(username, password, "customer");
        this.account = new Account(100); // Initial balance of 100
        updateCustomerLevel(); // Set initial customer level
    }

    public void deposit(double amount) {
        account.deposit(amount);
        updateCustomerLevel(); // Update level after deposit
    }

    public void withdraw(double amount) {
        account.withdraw(amount);
        updateCustomerLevel(); // Update level after withdrawal
    }

    public double getBalance() {
        return account.getBalance();
    }

    public void makePurchase(double amount) throws Exception {
        this.customerLevel.purchase(this, amount);
        updateCustomerLevel(); // Update the customer level in case the balance change affects the level
    }


    private void updateCustomerLevel() {
        double balance = this.account.getBalance();
        if (balance < 10000) {
            this.customerLevel = new SilverState();
        } else if (balance >= 10000 && balance < 20000) {
            this.customerLevel = new GoldState();
        } else if (balance >= 20000) {
            this.customerLevel = new PlatinumState();
        }
    }
    public State getState() {
        return this.customerLevel;
    }

}
