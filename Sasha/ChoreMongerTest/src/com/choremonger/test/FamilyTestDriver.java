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
		User user1 = UserImpl.createUser();
		User user2 = UserImpl.createUser();
		chore0.addUser(user0);
		chore0.addUser(user1);
		chore0.addUser(user2);
		
		ChoreImpl chore1 = new ChoreImpl(temp);
		test_users = chore1.getUsers();
		System.out.println("User 0 id: "+test_users.get(0).getId());
		System.out.println("User 1 id: "+test_users.get(1).getId());
		System.out.println("User 2 id: "+test_users.get(2).getId());
		
		chore0.removeUser(user1);
	
		ChoreImpl chore2 = new ChoreImpl(temp);
		test_users = chore2.getUsers();
		System.out.println("User 0 id: "+test_users.get(0).getId());
		System.out.println("User 1 id: "+test_users.get(1).getId());
		
		UserImpl.deleteUser(user0.getId());
		UserImpl.deleteUser(user1.getId());
		UserImpl.deleteUser(user2.getId());
		ChoreImpl.deleteChore(chore0.getId());
		ChoreImpl.deleteChore(chore1.getId());
		ChoreImpl.deleteChore(chore2.getId());
	}

}
