package com.choremonger.server;

import javax.ws.rs.Path;

import com.choremonger.shared.User;

@Path("/user")
public class UserResourceImpl implements UserResource {

	@Override
	public User createUser(User user) {
		return user;
	}

	@Override
	public void deleteUser(String id) {
		return;
	}

	@Override
	public User getUser(String id) {
		UserImpl retval = new UserImpl();
		retval.setId(id);
		return retval;
	}

	@Override
	public UserList getUsers() {
		UserList retval = new UserList();
		retval.addUser(new UserImpl());
		return retval;
	}

	@Override
	public void updateUser(String id, User user) {
		return;
	}

}
