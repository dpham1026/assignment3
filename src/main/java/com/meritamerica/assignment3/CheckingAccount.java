package com.meritamerica.assignment3;

import java.text.*;

public class CheckingAccount extends BankAccount {
	
	private static final double CHECKING_RATE = 0.0001;

	public CheckingAccount(double balance) {
		super(balance, CHECKING_RATE);
	}
	public CheckingAccount(long accountNumber, double balance, double interestRate, java.util.Date accountOpenedOn) {
		super(accountNumber, balance, interestRate, accountOpenedOn);
	}
	
	static CheckingAccount readFromString(String accountData) throws ParseException {

	}
}