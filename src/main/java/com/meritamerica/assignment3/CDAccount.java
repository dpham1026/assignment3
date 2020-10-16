package com.meritamerica.assignment3;

import java.util.Date;

public class CDAccount extends BankAccount {
	private CDOffering offering;
	private Date date;
	private int term;

		
	public CDAccount(CDOffering offering, double openBalance){
		super(openBalance, offering.getInterestRate());
		this.date = new Date();
		this.offering = offering;
	}
	public CDAccount(long accountNumber, double balance, double interestRate, java.util.Date accountOpenedOn, int term) {
		super(accountNumber, balance, interestRate, accountOpenedOn);
		this.term = term;
	}
	
	public int getTerm() {
		return offering.getTerm();
	}
	
	public Date getStartDate(){
		return date;
	}
	
	public double futureValue() {
		return balance * Math.pow(1 + getInterestRate(), getTerm());
	}
	public boolean withdraw() {
		return false;
	}

	public boolean deposit() {
		return false;
	}
}