package com.meritamerica.assignment3;

import java.text.*;
import java.util.Date;

public class CDAccount extends BankAccount {
	private Date date;
	private int term;

		
	public CDAccount(CDOffering offering, double openBalance){
		super(openBalance, offering.getInterestRate());
		this.accountNumber = getAccountNumber();
		this.term = offering.getTerm();
		this.date = accountOpenedOn();
		
	}
	public CDAccount(long accountNumber, double balance, double interestRate, java.util.Date accountOpenedOn, int term) {
		super(accountNumber, balance, interestRate, accountOpenedOn);
		this.term = term;
	}
	
	public int getTerm() {
		return term;
	}
	
	public Date getStartDate(){
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
	static CDAccount readFromString(String accountData) throws ParseException {
			String[] cda = accountData.split(",");
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(cda[3]);
			CDAccount newAccount = new CDAccount(Long.valueOf(cda[0]), Double.valueOf(cda[1]), Double.valueOf(cda[2]), date, Integer.valueOf(cda[4]));
			return newAccount;

	}
	String writeToString() {
		return getAccountNumber() + "," + getBalance() + "," + getInterestRate() + "," + term + "," + getOpenedOn();
	}
}