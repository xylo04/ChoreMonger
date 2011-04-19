package com.choremonger.test;

import com.choremonger.shared.Chore;

public class FamilyTestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Driver is asking to get Chore");
		String temp;
		Chore family = new ChoreImpl();

		System.out.println("\n\nMade family:");
		System.out.println("Name: "+ family.getName());
		System.out.println("Id:" + family.getId());
		temp = family.getId();
		family.setName("New Chore");
		System.out.println("Name: "+ family.getName());
		System.out.println("retrevng chore by id");
		Chore chore = new ChoreImpl(temp);
		System.out.println("Name: "+ chore.getName());
		System.out.println("Id:" + chore.getId());
		

	}

}
