package com.choremonger.test;

import java.sql.Date;

import com.choremonger.shared.Chore;
import com.choremonger.shared.User;

public class UserTestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date DoB = new Date(1929384998);
		System.out.println(DoB.toString());
		System.out.println("Driver is asking to get user");
		User user = UserImpl.createUser();
		if (user == null) {
			System.out.println("user was null!");
			System.exit(1);
		}
		user.setName("Bob");
		user = UserImpl.getUserByName("Bob");
		

	}

}
