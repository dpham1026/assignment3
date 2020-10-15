package com.meritamerica.assignment3;

import java.util.Date;

public class CDAccount extends BankAccount {
	CDOffering offering;
	Date date;

		
	public CDAccount(CDOffering offering, double openBalance){
		super(MeritBank.getNextAccountNumber(), openBalance, offering.getInterestRate());
		this.date = new Date();
		this.offering = offering;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	
	public int getTerm() {
		return offering.getTerm();
	}
	
	public Date getStartDate(){
		return date;
	}
	
	public long getAccountNumber() {
		return accountNumber;	
	}
	
	public double futureValue() {
		return balance*Math.pow(1 + getInterestRate(), getTerm());
	}

}