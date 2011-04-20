package com.choremonger.test;

import com.choremonger.shared.Family;

public class FamilyTestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// CREATE
		System.out.println("Driver is asking to create a family");
		Family family = FamilyImpl.createFamily("Test Family");
		if (family == null) {
			System.out.println("Family was null!");
			System.exit(1);
		}
		System.out.println("\nCreated family:");
		System.out.println(family.getName());
		System.out.println(family.getId());

		// RETRIEVE
		System.out.println("\n\nDriver is asking to get the family");
		family = FamilyImpl.getFamily(family.getId());
		if (family == null) {
			System.out.println("Family was null!");
			System.exit(1);
		}
		System.out.println("\nGot back a family:");
		System.out.println(family.getName());
		System.out.println(family.getId());

		// UPDATE
		System.out.println("\n\nDriver is asking to update the family");
		family.setName("Test family changed");
		System.out.println("Apparently ok.");

		// DELETE
		System.out.println("\n\nDriver is asking to delete the family");
		boolean ok = FamilyImpl.deleteFamily(family.getId());
		if (ok) {
			System.out.println("Apparently ok.");
		} else {
			System.out.println("Apparently NOT ok.");
		}
	}

}
