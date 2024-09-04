package coe528.project;

public class SilverState implements State {

    @Override
    public void purchase(Customer customer, double amount) throws Exception {
        final double fee = 20.00; // Fixed fee for silver state
        if (amount < 50) {
            throw new Exception("Minimum purchase amount is $50");
        }
        if (customer.getBalance() < amount + fee) {
            throw new Exception("Insufficient funds for purchase and fee.");
        }
        customer.withdraw(amount + fee); // Withdraw the purchase amount plus fee
    }
}
