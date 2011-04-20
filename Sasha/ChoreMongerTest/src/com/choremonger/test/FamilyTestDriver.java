package com.choremonger.test;

import java.util.List;

import com.choremonger.shared.Chore;
import com.choremonger.shared.User;

public class FamilyTestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Driver is asking to get Chore");
		String temp;
		List<User> test_users;
		
		
		ChoreImpl chore0 = new ChoreImpl();
		temp = chore0.getId();

		User user0 = UserImpl.createUser();
		chore0.addUser(user0);
		
		ChoreImpl chore1 = new ChoreImpl(temp);
		test_users = chore1.getUsers();
		System.out.println("User id: "+test_users.get(0).getId());
	
	//		chore0.clean();
//		chore1.clean();
	}

}
