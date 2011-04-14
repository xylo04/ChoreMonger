package com.choremonger.server;

import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Path("/user")
public class UserResourceImpl implements UserResource {

	@Override
	public UserImpl createUser(UserImpl toCreate) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(toCreate);
		} finally {
			pm.close();
		}

		return toCreate;
	}

	@Override
	public void deleteUser(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		UserImpl toDelete = null;
		try {
			Key k = KeyFactory.createKey(UserImpl.class.getSimpleName(), id);
			toDelete = pm.getObjectById(UserImpl.class, k);
			pm.deletePersistent(toDelete);
		} catch (JDOObjectNotFoundException e) {
			throw new WebApplicationException(404);
		} finally {
			pm.close();
		}

		return;
	}

	@Override
	public UserImpl getUser(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		UserImpl retval = null;
		try {
			Key k = KeyFactory.createKey(UserImpl.class.getSimpleName(), id);
			retval = pm.getObjectById(UserImpl.class, k);
		} catch (JDOObjectNotFoundException e) {
			throw new WebApplicationException(404);
		} finally {
			pm.close();
		}

		return retval;
	}

	@Override
	public UserList getUsers() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		UserList retval = new UserList();
		try {
			Query query = pm.newQuery(RewardImpl.class);
			@SuppressWarnings("unchecked")
			List<UserImpl> persistChoreList = (List<UserImpl>) query.execute();
			retval.addAllUsers(persistChoreList);
		} catch (JDOObjectNotFoundException e) {
			throw new WebApplicationException(404);
		} finally {
			pm.close();
		}

		return retval;
	}

	@Override
	public void updateUser(String id, UserImpl newValue) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		@SuppressWarnings("unused")
		UserImpl toUpdate = null;
		try {
			Key k = KeyFactory.createKey(UserImpl.class.getSimpleName(), id);
			toUpdate = pm.getObjectById(UserImpl.class, k);
			toUpdate = newValue;
		} catch (JDOObjectNotFoundException e) {
			throw new WebApplicationException(404);
		} finally {
			pm.close();
		}

		return;
	}

}
