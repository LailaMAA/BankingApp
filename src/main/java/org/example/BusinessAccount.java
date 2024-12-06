package org.example;

public class BusinessAccount extends BankAccount {
    private final double transactionFee = 5.0;

    public BusinessAccount(String accountHolder, double initialBalance) {
        super(accountHolder, initialBalance);
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited to Business: " + amount);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        double totalAmount = amount + transactionFee;
        if (totalAmount > balance) {
            throw new InsufficientFundsException("Insufficient funds in Business Account after transaction fee.");
        }
        balance -= totalAmount;
        System.out.println("Withdrawn from Business (including fee): " + totalAmount);
    }
}
