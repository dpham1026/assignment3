package com.meritamerica.assignment3;

public class MeritAmericaBankApp {
	public static void main(String[] args) {
		MeritBank.readFromFile("Bankinfo.txt");
		MeritBank.sortAccountHolders();
	}
}