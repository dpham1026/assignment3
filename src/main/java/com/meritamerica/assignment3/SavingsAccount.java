package com.meritamerica.assignment3;

import java.text.*;

public class SavingsAccount extends BankAccount {
	
	private static final double SAVINGS_RATE = 0.01;

	public SavingsAccount(double balance) {
		super(balance, SAVINGS_RATE);
	}
	static SavingsAccount readFromString(String accountData) throws ParseException {

	}
}