package coe528.project;

public class PlatinumState implements State {

    @Override
    public void purchase(Customer customer, double amount) throws Exception {
        if (amount < 50) {
            throw new Exception("Minimum purchase amount is $50");
        }
        if (customer.getBalance() < amount) {
            throw new Exception("Insufficient funds for purchase.");
        }
        customer.withdraw(amount); // Withdraw the purchase amount only, no fee
    }
}
