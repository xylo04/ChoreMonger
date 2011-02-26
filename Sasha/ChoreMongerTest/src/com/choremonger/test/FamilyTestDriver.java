package com.choremonger.test;

import com.choremonger.shared.Chore;

public class FamilyTestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Driver is asking to get Chore");
		String temp;
<<<<<<< HEAD
		ChoreImpl chore0 = new ChoreImpl();

		System.out.println("Instructions: " + chore0.getInstructions());
		System.out.println("PointValue: " + chore0.getPointValue());
		temp = chore0.getId();
		chore0.setInstructions("Test every funciton in the chore");
		chore0.setPointValue(56);
		ChoreImpl chore1 = new ChoreImpl(temp);
		System.out.println("Instructions: " + chore1.getInstructions());
		System.out.println("PointValue: " + chore1.getPointValue());
=======
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
>>>>>>> 2992908f1b24c6903dd1f2c3977204a503f16fb1
		

	}

}
