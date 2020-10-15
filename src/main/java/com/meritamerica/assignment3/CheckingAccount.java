package com.meritamerica.assignment3;

import java.text.*;

public class CheckingAccount extends BankAccount {
	
	private static final double CHECKING_RATE = 0.0001;

	public CheckingAccount(double balance) {
		super(balance, CHECKING_RATE);
	}
	static CheckingAccount readFromString(String accountData) throws ParseException {
		
	}
}