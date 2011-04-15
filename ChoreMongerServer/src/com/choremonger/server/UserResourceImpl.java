package com.choremonger.server;

import java.util.List;
import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Path("/user")
public class UserResourceImpl implements UserResource {

	private Logger log = Logger.getLogger(UserResourceImpl.class.getName());

	@Override
	public UserImpl createUser(UserImpl toCreate) {
		log.info("Creating username " + toCreate.getName());
		PersistenceManager pm = PMF.get().getPersistenceManager();
		UserImpl created = null;
		try {
			created = new UserImpl();
			pm.makePersistent(created);
			log.info("User got ID " + created.getId());
			created.setName(toCreate.getName());
		} catch (Exception e) {
			log.warning(e.getMessage());
		} finally {
			pm.close();
		}

		return created;
	}

	@Override
	public void deleteUser(String id) {
		log.info("Deleting user id " + id);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		UserImpl toDelete = null;
		try {
			Key k = KeyFactory.stringToKey(id);
			toDelete = pm.getObjectById(UserImpl.class, k);
			pm.deletePersistent(toDelete);
			log.info("OK");
		} catch (JDOObjectNotFoundException e) {
			log.warning("Not found");
			throw new WebApplicationException(404);
		} finally {
			pm.close();
		}

		return;
	}

	@Override
	public UserImpl getUser(String id) {
		log.info("Retrieve user id " + id);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		UserImpl retval = null;
		try {
			Key k = KeyFactory.stringToKey(id);
			retval = pm.getObjectById(UserImpl.class, k);
			log.info("OK");
		} catch (JDOObjectNotFoundException e) {
			log.warning("Not found");
			throw new WebApplicationException(404);
		} finally {
			pm.close();
		}

		return retval;
	}

	@Override
	public UserList getUsers() {
		log.info("Retrieve user list");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		UserList retval = new UserList();
		try {
			Query query = pm.newQuery(RewardImpl.class);
			@SuppressWarnings("unchecked")
			List<UserImpl> persistChoreList = (List<UserImpl>) query.execute();
			retval.addAllUsers(persistChoreList);
			log.info("OK");
		} catch (JDOObjectNotFoundException e) {
			log.warning("Not found");
			throw new WebApplicationException(404);
		} finally {
			pm.close();
		}

		return retval;
	}

	@Override
	public void updateUser(String id, UserImpl newValue) {
		log.info("Update user id " + id);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		@SuppressWarnings("unused")
		UserImpl toUpdate = null;
		try {
			Key k = KeyFactory.stringToKey(id);
			toUpdate = pm.getObjectById(UserImpl.class, k);
			toUpdate = newValue;
			log.info("OK");
		} catch (JDOObjectNotFoundException e) {
			log.warning("Not found");
			throw new WebApplicationException(404);
		} finally {
			pm.close();
		}

		return;
	}

}
