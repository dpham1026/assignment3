package com.meritamerica.assignment3;

import java.io.*;
import java.text.*;
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
			String currentLine = reader.readLine();
			while ((currentLine) != null && currentLine != "") {
				nextAccount = Integer.valueOf(currentLine);
				currentLine = reader.readLine();
				myCDOffering = new CDOffering[Integer.valueOf(currentLine)];
				for (int i = 0; i < myCDOffering.length; i++) {
					currentLine = reader.readLine();
					myCDOffering[i] = (CDOffering.readFromString(currentLine));
				}
				currentLine = reader.readLine();
				myAccountHolder = new AccountHolder[Integer.valueOf(currentLine)];
				for (int i = 0; i < myAccountHolder.length; i++) {
					currentLine = reader.readLine();
					myAccountHolder[i] = addAccountHolder(AccountHolder.readFromString(currentLine));
					int numChecking = Integer.valueOf(reader.readLine());
					for (int j = 0; j < numChecking; j++) {
						currentLine = reader.readLine();
						myAccountHolder[i].addCheckingAccount(CheckingAccount.readFromString(currentLine));
					}
					int numSavings = Integer.valueOf(reader.readLine());
					for (int j = 0; j < numSavings; j++) {
						currentLine = reader.readLine();
						myAccountHolder[i].addSavingsAccount(SavingsAccount.readFromString(currentLine));
					}
					int numCD = Integer.valueOf(reader.readLine());
					for (int j = 0; j < numCD; j++) {
						currentLine = reader.readLine();
						myAccountHolder[i].addCDAccount(CDAccount.readFromString(currentLine));
					}
				}
			}
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			return false;
		} catch (ParseException pe) {
			pe.printStackTrace();
			return false;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			return true;
		}
	}

	static boolean writeToFile(String fileName) {

		try {
			FileWriter file = new FileWriter(fileName);
			BufferedWriter writer = new BufferedWriter(file);
			writer.write((int) getNextAccountNumber());
			writer.newLine();
			writer.write(myCDOffering.length);
			writer.newLine();
			for (int i = 0; i < myCDOffering.length; i++) {
				writer.write(myCDOffering[i].writeToString());
				writer.newLine();
			}
			writer.write(myAccountHolder.length);
			writer.newLine();
			for (int i = 0; i < myAccountHolder.length; i++) {
				writer.write(myAccountHolder[i].writeToString());
				writer.newLine();
				writer.write(myAccountHolder[i].checkingarray.length);
				writer.newLine();
				for (int j = 0; j < myAccountHolder[i].getNumberOfCheckingAccounts(); j++) {
					writer.write(myAccountHolder[i].checkingarray[j].writeToString());
					writer.newLine();
				}
				writer.write(myAccountHolder[i].savingsarray.length);
				writer.newLine();
				for (int k = 0; k < myAccountHolder[i].getNumberOfSavingsAccounts(); k++) {
					writer.write(myAccountHolder[i].savingsarray[k].writeToString());
					writer.newLine();
				}
				writer.write(myAccountHolder[i].numberOfCDAccounts.length);
				writer.newLine();
				for (int l = 0; l < myAccountHolder[i].getNumberOfCDAccounts(); l++) {
					writer.write(myAccountHolder[i].numberOfCDAccounts[l].writeToString());
					writer.newLine();
				}
			}
		} 
			catch (Exception e) {
			System.out.println("Cannot Read File");
			return false;
		} finally {
			return true;
		}
	}

	static AccountHolder[] sortAccountHolders() {
		ArrayList<AccountHolder> ac = new ArrayList<AccountHolder>();
		for (int i = 0; i < myAccountHolder.length; i++) {
			ac.add(myAccountHolder[i]);
		}
		Collections.sort(ac);
		AccountHolder[] newAC = myAccountHolder;
		for (int i = 0; i < myAccountHolder.length; i++) {
			newAC[i] = ac.get(i);
		}
		return newAC;
	}

	static void setNextAccountNumber(long accountNumber) {
		nextAccount = accountNumber;
	}
}