package com.choremonger.server;

import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Path("/family")
public class FamilyResourceImpl implements FamilyResource {

	@Override
	public FamilyImpl createFamily(FamilyImpl toCreate) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(toCreate);
		} finally {
			pm.close();
		}

		return toCreate;
	}

	@Override
	public void deleteFamily(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		FamilyImpl toDelete = null;
		try {
			Key k = KeyFactory.createKey(FamilyImpl.class.getSimpleName(), id);
			toDelete = pm.getObjectById(FamilyImpl.class, k);
			pm.deletePersistent(toDelete);
		} catch (JDOObjectNotFoundException e) {
			throw new WebApplicationException(404);
		} finally {
			pm.close();
		}

		return;
	}

	@Override
	public FamilyImpl getFamily() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		FamilyImpl retval = null;
		try {
			Query query = pm.newQuery(FamilyImpl.class);
			query.setRange(0L, 1L);
			@SuppressWarnings("unchecked")
			List<FamilyImpl> families = (List<FamilyImpl>) query.execute();
			retval = families.get(0);
		} catch (JDOObjectNotFoundException e) {
			throw new WebApplicationException(404);
		} finally {
			pm.close();
		}

		return retval;
	}

	@Override
	public FamilyImpl getFamily(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		FamilyImpl retval = null;
		try {
			Key k = KeyFactory.createKey(FamilyImpl.class.getSimpleName(), id);
			retval = pm.getObjectById(FamilyImpl.class, k);
		} catch (JDOObjectNotFoundException e) {
			throw new WebApplicationException(404);
		} finally {
			pm.close();
		}

		return retval;
	}

	@Override
	public void updateFamily(String id, FamilyImpl newValue) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		@SuppressWarnings("unused")
		FamilyImpl toUpdate = null;
		try {
			Key k = KeyFactory.createKey(FamilyImpl.class.getSimpleName(), id);
			toUpdate = pm.getObjectById(FamilyImpl.class, k);
			toUpdate = newValue;
		} catch (JDOObjectNotFoundException e) {
			throw new WebApplicationException(404);
		} finally {
			pm.close();
		}

		return;
	}

}
