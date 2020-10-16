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
		String[] sa = accountData.split(",");
		Date date = new SimpleDateFormat("MM/dd/yyyy").parse(sa[3]);
		SavingsAccount newAccount = new SavingsAccount(Long.valueOf(sa[0]), Double.valueOf(sa[1]), Double.valueOf(sa[2]), date);
		return newAccount;
	}
}