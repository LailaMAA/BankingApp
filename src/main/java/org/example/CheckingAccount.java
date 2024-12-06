package org.example;

public class CheckingAccount extends BankAccount {
    public CheckingAccount(String accountHolder, double initialBalance) {
        super(accountHolder, initialBalance);
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited to Checking: " + amount);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds in Checking Account.");
        }
        balance -= amount;
        System.out.println("Withdrawn from Checking: " + amount);
    }
}
