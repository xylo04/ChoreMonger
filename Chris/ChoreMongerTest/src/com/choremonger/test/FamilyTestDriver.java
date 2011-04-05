package com.choremonger.test;

import com.choremonger.shared.Family;

public class FamilyTestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Driver is asking to get family");
		Family family = FamilyImpl.getFamily("1");
		if (family == null) {
			System.out.println("Family was null!");
			System.exit(1);
		}

		System.out.println("\n\nGot back a family:");
		System.out.println(family.getName());
		System.out.println(family.getId());

	}

}
