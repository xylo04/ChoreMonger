package com.choremonger.test;

import com.choremonger.shared.Chore;

public class FamilyTestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Driver is asking to get Chore");
		String temp;
		ChoreImpl chore0 = new ChoreImpl();

		System.out.println("Instructions: " + chore0.getInstructions());
		System.out.println("PointValue: " + chore0.getPointValue());
		temp = chore0.getId();
		chore0.setInstructions("Test every funciton in the chore");
		chore0.setPointValue(56);
		ChoreImpl chore1 = new ChoreImpl(temp);
		System.out.println("Instructions: " + chore1.getInstructions());
		System.out.println("PointValue: " + chore1.getPointValue());
		

	}

}
