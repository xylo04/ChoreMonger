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

@Path("/chore")
public class ChoreResourceImpl implements ChoreResource {

	private Logger log = Logger.getLogger(ChoreResourceImpl.class.getName());

	@Override
	public ChoreImpl createChore(ChoreImpl toCreate) {
		log.info("Create chore " + toCreate.getName());
		FamilyResourceImpl fr = new FamilyResourceImpl();
		FamilyImpl f = fr.getFamily();

		f.addChore(toCreate);
		fr.updateFamily(f.getId(), f);
		log.info("Chore got id " + toCreate.getId());
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(toCreate);
			log.info("Chore got id " + toCreate.getId());
		} catch (Exception e) {
			log.warning(e.getMessage());
		} finally {
			pm.close();
		}

		return toCreate;
	}

	@Override
	public void deleteChore(String id) {
		log.info("Deleting chore id " + id);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ChoreImpl toDelete = null;
		try {
			Key k = KeyFactory.stringToKey(id);
			toDelete = pm.getObjectById(ChoreImpl.class, k);
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
	public ChoreImpl getChore(String id) {
		log.info("Retrieve chore id " + id);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ChoreImpl retval = null;
		try {
			Key k = KeyFactory.stringToKey(id);
			retval = pm.getObjectById(ChoreImpl.class, k);
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
	public ChoreList getChores() {
		log.info("Retrieve chore list");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ChoreList retval = new ChoreList();
		try {
			Query query = pm.newQuery(ChoreImpl.class);
			@SuppressWarnings("unchecked")
			List<ChoreImpl> persistChoreList = (List<ChoreImpl>) query
					.execute();
			retval.addAllChores(persistChoreList);
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
	public void updateChore(String id, ChoreImpl newValue) {
		log.info("Update chore id " + id);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		@SuppressWarnings("unused")
		ChoreImpl toUpdate = null;
		try {
			Key k = KeyFactory.stringToKey(id);
			toUpdate = pm.getObjectById(ChoreImpl.class, k);
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
