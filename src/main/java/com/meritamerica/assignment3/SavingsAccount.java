package com.meritamerica.assignment3;

import java.text.*;

public class SavingsAccount extends BankAccount {
	
	private static final double SAVINGS_RATE = 0.01;

	public SavingsAccount(double balance) {
		super(balance, SAVINGS_RATE);
	}
	public SavingsAccount(long accountNumber, double balance, double interestRate, java.util.Date accountOpenedOn) {
		super(accountNumber, balance, interestRate, accountOpenedOn);
	}
	static SavingsAccount readFromString(String accountData) throws ParseException {

	}
}