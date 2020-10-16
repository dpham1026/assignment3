package com.meritamerica.assignment3;

import java.io.*;
import java.util.*;

public class MeritBank {
	static AccountHolder myAccountHolder[] = new AccountHolder[0];
	static CDOffering myCDOffering[] = new CDOffering[0];
	static CDAccount myCDAccount[] = new CDAccount[0];
	private static long nextAccount = 0;

	public static AccountHolder addAccountHolder(AccountHolder accountHolder) {
		AccountHolder[] myAccountHolder1 = new AccountHolder[myAccountHolder.length + 1];
		for (int i = 0; i < myAccountHolder.length; i++) {
			myAccountHolder1[i] = myAccountHolder[i];
		}
		myAccountHolder = myAccountHolder1;
		return myAccountHolder[myAccountHolder.length - 1] = accountHolder;

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
				  myCDOffering[i] = (CDOffering.readFromString(reader.readLine()));
			  }
			  myAccountHolder = new AccountHolder[Integer.valueOf(reader.readLine())];
			  for(int i = 0; i < myAccountHolder.length; i++) {
				  myAccountHolder[i] = addAccountHolder(AccountHolder.readFromString(reader.readLine()));
				  int numChecking = Integer.valueOf(reader.readLine());
				  for(int j = 0; j < numChecking; j++) {
					  myAccountHolder[i].addCheckingAccount(CheckingAccount.readFromString(reader.readLine()));
				  }
				  int numSavings = Integer.valueOf(reader.readLine());
				  for(int j = 0; j < numSavings; j++) {
					  myAccountHolder[i].addSavingsAccount(SavingsAccount.readFromString(reader.readLine()));
				  }
				  int numCD = Integer.valueOf(reader.readLine());
				  for(int j = 0; j < numCD; j++) {
					  myAccountHolder[i].addCDAccount(CDAccount.readFromString(reader.readLine()));
				  }
			  }
		}
		reader.close();
		} catch (Exception ex) {
			System.out.println("Unable to open file.");
			return false;
		}
		return true;
	}
	static boolean writeToFile(String fileName) {
		
		try {
			FileWriter file = new FileWriter(fileName);
			BufferedWriter writer = new BufferedWriter(file);
			writer.write((int) getNextAccountNumber());
			writer.newLine(); 
			writer.write(myCDOffering.length);
			writer.newLine();
			for(int i = 0; i < myCDOffering.length; i++) {
				writer.write(myCDOffering[i].writeToString());
				writer.newLine();
			}
			writer.write(myAccountHolder.length);
			writer.newLine();
			for(int i = 0; i < myAccountHolder.length; i++) {
				writer.write(myAccountHolder[i].writeToString());
				writer.newLine();
				writer.write(myAccountHolder[i].checkingarray.length);
				writer.newLine();
				for(int j = 0; j < myAccountHolder[i].getNumberOfCheckingAccounts(); j++) {
					writer.write(myAccountHolder[i].checkingarray[j].writeToString());
					writer.newLine();
				}
				writer.write(myAccountHolder[i].savingsarray.length);
				writer.newLine();
				for(int j = 0; j < myAccountHolder[i].getNumberOfSavingsAccounts(); j++) {
					writer.write(myAccountHolder[i].savingsarray[j].writeToString());
					writer.newLine();
				}
				writer.write(myAccountHolder[i].numberOfCDAccounts.length);
				writer.newLine();
				for(int j = 0; j < myAccountHolder[i].getNumberOfCDAccounts(); j++) {
					writer.write(myAccountHolder[i].numberOfCDAccounts[j].writeToString());
					writer.newLine();
				}
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Cannot Read File");
			return false;
		}
		return true;
	}
	static AccountHolder[] sortAccountHolders() {
		ArrayList<AccountHolder> ac = new ArrayList<AccountHolder>();
		for(int i = 0; i < myAccountHolder.length; i++) {
			ac.add(myAccountHolder[i]);
		}
		Collections.sort(ac);
		AccountHolder[] newAC = myAccountHolder;
		for(int i = 0; i < myAccountHolder.length; i++) {
			newAC[i] = ac.get(i);
		}
		return newAC;
	}
	static void setNextAccountNumber(long nextAccountNumber) {
		nextAccount = nextAccountNumber;
	}
}