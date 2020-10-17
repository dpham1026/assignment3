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
	
	public static CheckingAccount readFromString(String accountData)throws ParseException {
    	
		String [] ca = accountData.split(",");
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		long accountNumber = Long.parseLong(ca[0]);
		double balance = Double.parseDouble(ca[1]);
		double interestRate = Double.parseDouble(ca[2]);
		Date accountOpenedOn = date.parse(ca[3]);
		CheckingAccount newCheckingAccount = new CheckingAccount(accountNumber, balance, interestRate, accountOpenedOn);
		return newCheckingAccount;
}   
} 	