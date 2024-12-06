package org.example;

public class SavingsAccount extends BankAccount {
    public SavingsAccount(String accountHolder, double initialBalance) {
        super(accountHolder, initialBalance);
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited to Savings: " + amount);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds in Savings Account.");
        }
        balance -= amount;
        System.out.println("Withdrawn from Savings: " + amount);
    }
}
