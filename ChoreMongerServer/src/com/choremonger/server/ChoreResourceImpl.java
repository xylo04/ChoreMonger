package com.choremonger.server;

import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Path("/chore")
public class ChoreResourceImpl implements ChoreResource {

	@Override
	public ChoreImpl createChore(ChoreImpl toCreate) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		toCreate.setId(null);
		try {
			pm.makePersistent(toCreate);
		} finally {
			pm.close();
		}

		return toCreate;
	}

	@Override
	public void deleteChore(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ChoreImpl toDelete = null;
		try {
			Key k = KeyFactory.createKey(ChoreImpl.class.getSimpleName(), id);
			toDelete = pm.getObjectById(ChoreImpl.class, k);
			pm.deletePersistent(toDelete);
		} catch (JDOObjectNotFoundException e) {
			throw new WebApplicationException(404);
		} finally {
			pm.close();
		}

		return;
	}

	@Override
	public ChoreImpl getChore(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ChoreImpl retval = null;
		try {
			Key k = KeyFactory.createKey(ChoreImpl.class.getSimpleName(), id);
			retval = pm.getObjectById(ChoreImpl.class, k);
		} catch (JDOObjectNotFoundException e) {
			throw new WebApplicationException(404);
		} finally {
			pm.close();
		}

		return retval;
	}

	@Override
	public ChoreList getChores() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ChoreList retval = new ChoreList();
		try {
			Query query = pm.newQuery(ChoreImpl.class);
			@SuppressWarnings("unchecked")
			List<ChoreImpl> persistChoreList = (List<ChoreImpl>) query
					.execute();
			retval.addAllChores(persistChoreList);
		} finally {
			pm.close();
		}

		return retval;
	}

	@Override
	public void updateChore(String id, ChoreImpl newValue) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		@SuppressWarnings("unused")
		ChoreImpl toUpdate = null;
		try {
			Key k = KeyFactory.createKey(ChoreImpl.class.getSimpleName(), id);
			toUpdate = pm.getObjectById(ChoreImpl.class, k);
			toUpdate = newValue;
		} catch (JDOObjectNotFoundException e) {
			throw new WebApplicationException(404);
		} finally {
			pm.close();
		}

		return;
	}

}
