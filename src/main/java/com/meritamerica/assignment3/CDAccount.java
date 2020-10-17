package com.meritamerica.assignment3;

import java.text.*;
import java.util.Date;

public class CDAccount extends BankAccount {
	Date date;
	private int term;

	public CDAccount(CDOffering offering, double openBalance) {
		super(openBalance, offering.getInterestRate());
		this.accountNumber = getAccountNumber();
		this.term = offering.getTerm();

	}

	public CDAccount(long accountNumber, double balance, double interestRate, java.util.Date accountOpenedOn,
			int term) {
		super(accountNumber, balance, interestRate, accountOpenedOn);
		this.term = term;
	}

	public int getTerm() {
		return term;
	}

	public Date getStartDate() {
		return date;
	}

	public double futureValue() {
		return balance * Math.pow(1 + getInterestRate(), getTerm());
	}

	@Override
	public boolean withdraw(double amount) {
		return false;
	}

	@Override
	public boolean deposit(double amount) {
		return false;
	}

	public static CDAccount readFromString(String accountData) throws ParseException, NumberFormatException {
		String[] cda = accountData.split(",");
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		long accountNumber = Long.parseLong(cda[0]);
		double balance = Double.parseDouble(cda[1]);
		double interestRate = Double.parseDouble(cda[2]);
		Date accountOpenedOn = date.parse(cda[3]);
		int term = Integer.parseInt(cda[4]);
		CDAccount newCDAccount = new CDAccount(accountNumber, balance, interestRate, accountOpenedOn, term);
		return newCDAccount;
	}

	String writeToString() {
		return getAccountNumber() + "," + getBalance() + "," + getInterestRate() + "," + term + "," + getOpenedOn();
	}
}