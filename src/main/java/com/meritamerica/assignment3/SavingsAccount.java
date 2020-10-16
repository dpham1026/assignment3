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
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		Date newDate = date.parse(sa[3]);
		SavingsAccount newAccount = new SavingsAccount(Long.parseLong(sa[0]), Double.parseDouble(sa[1]), Double.parseDouble(sa[2]), newDate);
		return newAccount;
	}
}