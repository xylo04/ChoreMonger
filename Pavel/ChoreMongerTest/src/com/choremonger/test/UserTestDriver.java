package com.choremonger.test;

import java.sql.Date;

import com.choremonger.shared.User;

public class UserTestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date DoB = new Date(1929384998);
		System.out.println(DoB.toString());
		System.out.println("Driver is asking to get user");
		User user = UserImpl.getUser("agtjaG9yZW1vbmdlcnIgCxIKRmFtaWx5SW1wbBjxLgwLEghVc2VySW1wbBjSDww");
		if (user == null) {
			System.out.println("user was null!");
			System.exit(1);
		}

		System.out.println("\n\nGot back a user:");
		System.out.println(user.getName());
		System.out.println(user.getId());
		user.setName("Sir Awesome will Die More Now! Mwahahaha");
		user.setEmail("death@gmail.com");
		user.setRewardPoints(90000);
		user.setDob(DoB);
		System.out.println(user.getId());
		System.out.println(user.getName());
		System.out.println(user.getRewardPoints());
		System.out.println(user.getDob().toString());

	}

}
