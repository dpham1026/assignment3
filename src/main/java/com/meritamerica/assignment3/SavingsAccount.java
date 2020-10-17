package com.meritamerica.assignment3;

import java.text.*;
import java.util.Date;

public class SavingsAccount extends BankAccount {
	
	private static final double SAVINGS_RATE = 0.01;

	public SavingsAccount(double balance) {
		super(balance, SAVINGS_RATE);
	}
	public SavingsAccount(long accountNumber, double balance, double interestRate, java.util.Date accountOpenedOn) {
		super(accountNumber, balance, interestRate, accountOpenedOn);
	}
	static SavingsAccount readFromString(String accountData) throws ParseException {
		String [] sa = accountData.split(",");
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		long accountNumber = Long.parseLong(sa[0]);
		double balance = Double.parseDouble(sa[1]);
		double interestRate = Double.parseDouble(sa[2]);
		Date accountOpenedOn = date.parse(sa[3]);
		SavingsAccount newSavingsAccount = new SavingsAccount(accountNumber, balance, interestRate, accountOpenedOn);
		return newSavingsAccount;
	}
}