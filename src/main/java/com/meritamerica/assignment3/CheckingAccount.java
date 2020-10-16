package com.meritamerica.assignment3;

import java.text.*;
import java.util.Date;

public class CheckingAccount extends BankAccount {
	
	private static final double CHECKING_RATE = 0.0001;

	public CheckingAccount(double balance) {
		super(balance, CHECKING_RATE);
	}
	public CheckingAccount(long accountNumber, double balance, double interestRate, java.util.Date accountOpenedOn) {
		super(accountNumber, balance, interestRate, accountOpenedOn);
	}
	static CheckingAccount readFromString(String accountData) throws ParseException {
		String[] ca = accountData.split(",");
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(ca[3]);
		CheckingAccount newAccount = new CheckingAccount(Long.valueOf(ca[0]), Double.valueOf(ca[1]), Double.valueOf(ca[2]), date);
		return newAccount;
	}
}