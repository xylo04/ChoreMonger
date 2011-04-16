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

@Path("/family")
public class FamilyResourceImpl implements FamilyResource {

	private Logger log = Logger.getLogger(FamilyResourceImpl.class.getName());

	@Override
	public FamilyImpl createFamily(FamilyImpl toCreate) {
		log.info("Creating family " + toCreate.getName());
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(toCreate);
			log.info("Family got ID " + toCreate.getId());
		} catch (Exception e) {
			log.warning(e.getMessage());
		} finally {
			pm.close();
		}

		return toCreate;
	}

	@Override
	public void deleteFamily(String id) {
		log.info("Deleting family id " + id);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		FamilyImpl toDelete = null;
		try {
			Key k = KeyFactory.stringToKey(id);
			toDelete = pm.getObjectById(FamilyImpl.class, k);
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
	public FamilyImpl getFamily() {
		log.info("Retrieve default family");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		FamilyImpl retval = null;
		try {
			Query query = pm.newQuery(FamilyImpl.class);
			query.setRange(0L, 1L);
			@SuppressWarnings("unchecked")
			List<FamilyImpl> families = (List<FamilyImpl>) query.execute();
			retval = families.get(0);
			log.info("OK");
		} catch (JDOObjectNotFoundException e) {
			log.warning("Not found");
			throw new WebApplicationException(404);
			// TODO catch IndexOutOfBoundsException
		} finally {
			pm.close();
		}

		return retval;
	}

	@Override
	public FamilyImpl getFamily(String id) {
		log.info("Retrieve family id " + id);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		FamilyImpl retval = null;
		try {
			Key k = KeyFactory.stringToKey(id);
			retval = pm.getObjectById(FamilyImpl.class, k);
			retval.getChores();
			log.info("OK");
		} catch (JDOObjectNotFoundException e) {
			log.warning("Not found");
			throw new WebApplicationException(404);
		} catch (IllegalArgumentException e) {
			log.warning("Couldn't parse, probably a bad id");
			throw new WebApplicationException(404);
		} finally {
			pm.close();
		}

		return retval;
	}

	@Override
	public void updateFamily(String id, FamilyImpl newValue) {
		log.info("Update family id " + id);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Key k = KeyFactory.stringToKey(id);
			FamilyImpl toUpdate = pm.getObjectById(FamilyImpl.class, k);
			toUpdate.copyFrom(newValue);
			pm.makePersistent(toUpdate);
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
