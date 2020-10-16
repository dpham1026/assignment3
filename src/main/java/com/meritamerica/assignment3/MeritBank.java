package com.meritamerica.assignment3;

import java.io.*;
import java.text.*;
import java.util.*;

public class MeritBank {
	static AccountHolder myAccountHolder[] = new AccountHolder[0];
	static CDOffering myCDOffering[] = new CDOffering[0];
	static CDAccount myCDAccount[] = new CDAccount[0];
	private static long nextAccount = 0;

	public static void addAccountHolder(AccountHolder accountHolder) {
		AccountHolder[] myAccountHolder1 = new AccountHolder[myAccountHolder.length + 1];
		for (int i = 0; i < myAccountHolder.length; i++) {
			myAccountHolder1[i] = myAccountHolder[i];
		}
		myAccountHolder = myAccountHolder1;
		myAccountHolder[myAccountHolder.length - 1] = accountHolder;

	}

	public static AccountHolder[] getAccountHolders() {
		return myAccountHolder;

	}

	public static CDOffering[] getCDOfferings() {
		return myCDOffering;

	}

	public static CDOffering getBestCDOffering(double depositAmount) {
		double best = 00;
		CDOffering CDO = null;
		if (myCDOffering == null) {
			return null;
		}
		for (int i = 0; i < myCDOffering.length; i++) {
			if (futureValue(depositAmount, CDO.getInterestRate(), CDO.getTerm()) > best) {
				CDO = myCDOffering[i];
				best = futureValue(depositAmount, CDO.getInterestRate(), CDO.getTerm());
			}
		}
		return CDO;
	}

	public static CDOffering getSecondBestCDOffering(double depositAmount) {
		double secondbest = 00;
		CDOffering CDO = null;
		CDOffering CDB = null;
		if (myCDOffering == null) {
			return null;
		}
		for (int i = 0; i < myCDOffering.length; i++) {
			if (futureValue(depositAmount, CDO.getInterestRate(), CDO.getTerm()) > secondbest) {
				CDB = CDO;
				CDO = myCDOffering[i];
				secondbest = futureValue(depositAmount, CDO.getInterestRate(), CDO.getTerm());
			}
		}
		return CDB;

	}

	public static void clearCDOfferings() {
		myCDOffering = null;

	}

	public static void setCDOfferings(CDOffering[] offerings) {
		myCDOffering = offerings;
	}

	public static long getNextAccountNumber() {
		return nextAccount++;
	}

	public static double totalBalances() {
		double total = 0;
		for (int i = 0; i < myAccountHolder.length; i++) {
			total += myAccountHolder[i].getCombinedBalance();
		}
		return total;

	}

	public static double futureValue(double presentValue, double interestRate, int term) {
		double value = 0.00;
		double powered = Math.pow((1 + interestRate), term);
		value = presentValue * powered;
		return value;
	}
	
	static boolean readFromFile(String fileName) {
		try {
		FileInputStream fileStream = new FileInputStream(fileName);
		InputStreamReader input = new InputStreamReader(fileStream);
		BufferedReader reader = new BufferedReader(input);
		while ((reader.readLine()) != null && reader.readLine() != "") {
			  nextAccount = Integer.valueOf(reader.readLine());
			  myCDOffering = new CDOffering[Integer.valueOf(reader.readLine())];
			  for(int i = 0; i < myCDOffering.length; i++) {
				  String[] cd = reader.readLine().split(",");
				  myCDOffering[i] = new CDOffering(Integer.valueOf(cd[0]), Double.valueOf(cd[1]));
			  }
			  myAccountHolder = new AccountHolder[Integer.valueOf(reader.readLine())];
			  for(int i = 0; i < myAccountHolder.length; i++) {
				  String[] ac = reader.readLine().split(",");
				  myAccountHolder[i] = new AccountHolder(ac[0], ac[1], ac[2], ac[3]);
				  int numChecking = Integer.valueOf(reader.readLine());
				  for(int j = 0; j < numChecking; j++) {
					  String[] ca = reader.readLine().split(",");
					  Date date = new SimpleDateFormat("MM/dd/yyyy").parse(ca[3]);
					  CheckingAccount newAccount = new CheckingAccount(Long.valueOf(ca[0]), Double.valueOf(ca[1]), Double.valueOf(ca[2]), date);
					  myAccountHolder[i].addCheckingAccount(newAccount);
				  }
				  int numSavings = Integer.valueOf(reader.readLine());
				  for(int j = 0; j < numSavings; j++) {
					  String[] sa = reader.readLine().split(",");
					  Date date = new SimpleDateFormat("MM/dd/yyyy").parse(sa[3]);
					  SavingsAccount newAccount = new SavingsAccount(Long.valueOf(sa[0]), Double.valueOf(sa[1]), Double.valueOf(sa[2]), date);
					  myAccountHolder[i].addSavingsAccount(newAccount);
				  }
				  int numCD = Integer.valueOf(reader.readLine());
				  for(int j = 0; j < numCD; j++) {
					  String[] cda = reader.readLine().split(",");
					  Date date = new SimpleDateFormat("MM/dd/yyyy").parse(cda[3]);
					  CDAccount newAccount = new CDAccount(Long.valueOf(cda[0]), Double.valueOf(cda[1]), Double.valueOf(cda[2]), date, Integer.valueOf(cda[4]));
					  myAccountHolder[i].addCDAccount(newAccount);
				  }
			  }
		}
		} catch (Exception ex) {
			System.out.println("Unable to open file.");
			return false;
		}
		return true;
	}
	static boolean writeToFile(String fileName) {
		
	}
	static AccountHolder[] sortAccountHolders() {
		
	}
	static void setNextAccountNumber(long nextAccountNumber) {
		nextAccount = nextAccountNumber;
	}
}