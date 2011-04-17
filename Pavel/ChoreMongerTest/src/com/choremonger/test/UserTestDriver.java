package com.choremonger.test;

import com.choremonger.shared.User;

public class UserTestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Driver is asking to get user");
		User user = UserImpl.createUser();
		if (user == null) {
			System.out.println("user was null!");
			System.exit(1);
		}

		System.out.println("\n\nGot back a user:");
		System.out.println(user.getName());
		System.out.println(user.getId());
		user.setName("Sir Awesome will Die");
		user.setEmail("specialperson@gmail.com");
		user.setRewardPoints(15);
		user = UserImpl.getUser(user.getId());
		UserImpl.deleteUser(user.getId());

	}

}
