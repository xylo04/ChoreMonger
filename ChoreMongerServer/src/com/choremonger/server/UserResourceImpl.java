package com.choremonger.server;

import java.util.logging.Logger;

import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;

import com.choremonger.shared.User;

@Path("/user")
public class UserResourceImpl implements UserResource {

	private Logger log = Logger.getLogger(UserResourceImpl.class.getName());

	@Override
	public UserImpl createUser(UserImpl toCreate) {
		log.info("Creating username " + toCreate.getName());
		toCreate.setId(null);
		FamilyResource fr = new FamilyResourceImpl();
		FamilyImpl f = fr.getFamily();
		f.addUser(toCreate);
		fr.updateFamily(f.getId(), f);
		log.info("User got id " + toCreate.getId());
		return toCreate;
	}

	@Override
	public void deleteUser(String id) {
		log.info("Delete user id " + id);
		boolean found = false;
		FamilyResource fr = new FamilyResourceImpl();
		FamilyImpl f = fr.getFamily();
		for (User u : f.getUsers()) {
			if (u.getId().equals(id)) {
				f.removeUser(u);
				found = true;
			}
		}
		if (found) {
			fr.updateFamily(f.getId(), f);
			log.info("OK");
		} else {
			log.warning("Not found");
		}
	}

	@Override
	public UserImpl getUser(String id) {
		log.info("Retrieve user id " + id);
		FamilyResource fr = new FamilyResourceImpl();
		FamilyImpl f = fr.getFamily();
		UserImpl retval = null;
		for (User u : f.getUsers()) {
			if (u.getId().equals(id)) {
				retval = (UserImpl) u;
			}
		}
		if (retval == null) {
			log.warning("Not found");
			throw new WebApplicationException(404);
		}
		log.info("OK");
		return retval;
	}

	@Override
	public UserList getUsers() {
		log.info("Retrieve user list");
		FamilyResource fr = new FamilyResourceImpl();
		FamilyImpl f = fr.getFamily();
		UserList retval = new UserList();
		for (User u : f.getUsers()) {
			retval.addUser((UserImpl) u);
		}
		log.info("OK");
		return retval;
	}

	@Override
	public void updateUser(String id, UserImpl newValue) {
		log.info("Update user id " + id);
		if (!newValue.getId().equals(id)) {
			// uri and xml document didn't refer to the same ID
			throw new WebApplicationException(400);
		}
		FamilyResource fr = new FamilyResourceImpl();
		FamilyImpl f = fr.getFamily();
		UserImpl toUpdate = null;
		for (User u : f.getUsers()) {
			if (u.getId().equals(id)) {
				toUpdate = (UserImpl) u;
			}
		}
		if (toUpdate == null) {
			log.warning("Not found");
			throw new WebApplicationException(404);
		}
		f.removeUser(toUpdate);
		f.addUser(newValue);
		fr.updateFamily(f.getId(), f);
		log.info("OK");
	}
}
