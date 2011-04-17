package com.choremonger.test;

import com.choremonger.shared.Chore;

public class FamilyTestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Driver is asking to get Chore");
		Chore family = new ChoreImpl();

		System.out.println("\n\nMade family:");
		System.out.println("Name: "+family.getName());
		System.out.println("Id:" + family.getId());

	}

}
