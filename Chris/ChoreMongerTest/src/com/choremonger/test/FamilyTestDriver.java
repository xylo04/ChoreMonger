package com.choremonger.test;

import com.choremonger.shared.Family;

public class FamilyTestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Driver is asking for new family");
		Family family = FamilyImpl.createFamily();
		if (family == null) {
			System.out.println("Family was null!");
			System.exit(1);
		}
		System.out.println(family.getName());
		System.out.println(family.getId());

	}

}
