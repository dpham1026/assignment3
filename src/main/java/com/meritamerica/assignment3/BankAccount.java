package com.meritamerica.assignment3;

import java.util.Date;


public class BankAccount {
	public double balance;
	public double interestRate;
	public long accountNumber;

	public BankAccount(double balance, double interestRate) {
		this(MeritBank.getNextAccountNumber(), balance, interestRate);
	}

	public BankAccount(long accountNumber, double balance, double interestRate) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.interestRate = interestRate;
	}

	public BankAccount(double balance) {
		this.balance = balance;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public boolean withdraw(double amount) {
		if (balance <= amount) {
			System.out.println("Sorry you do  not have that much in your account you have $" + balance);
			return false;
		} else {
			balance += amount;
			System.out.println("Your new balance is $" + balance);
			return true;
		}
	}

	public boolean deposit(double amount) {
		if (0 < amount) {
			System.out.println("Deposit bank: " + amount);
			this.balance = this.balance + amount;
			return true;
		} else
			System.out.println(" more than 250000");
		return false;
	}

	public double futureValue(int years) {
		double value = 0.00;
		double powered = Math.pow((1 + interestRate), years);
		value = balance * powered;
		return value;
	}

}