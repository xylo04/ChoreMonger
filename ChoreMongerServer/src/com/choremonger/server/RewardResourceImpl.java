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

@Path("/reward")
public class RewardResourceImpl implements RewardResource {

	private Logger log = Logger.getLogger(RewardResourceImpl.class.getName());

	@Override
	public RewardImpl createReward(RewardImpl toCreate) {
		log.info("Create reward " + toCreate.getName());
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(toCreate);
			log.info("Reward got ID " + toCreate.getId());
		} catch (Exception e) {
			log.warning(e.getMessage());
		} finally {
			pm.close();
		}

		return toCreate;
	}

	@Override
	public void deleteReward(String id) {
		log.info("Delete reward id " + id);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		RewardImpl toDelete = null;
		try {
			Key k = KeyFactory.stringToKey(id);
			toDelete = pm.getObjectById(RewardImpl.class, k);
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
	public RewardImpl getReward(String id) {
		log.info("Retrieve reward id " + id);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		RewardImpl retval = null;
		try {
			Key k = KeyFactory.stringToKey(id);
			retval = pm.getObjectById(RewardImpl.class, k);
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
	public RewardList getRewards() {
		log.info("Get rewards list");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		RewardList retval = new RewardList();
		try {
			Query query = pm.newQuery(RewardImpl.class);
			@SuppressWarnings("unchecked")
			List<RewardImpl> persistChoreList = (List<RewardImpl>) query
					.execute();
			retval.addAllRewards(persistChoreList);
			log.info("OK");
		} catch (JDOObjectNotFoundException e) {
			log.warning("Not Found");
			throw new WebApplicationException(404);
		} finally {
			pm.close();
		}

		return retval;
	}

	@Override
	public void updateReward(String id, RewardImpl newValue) {
		log.info("Update reward id " + id);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		@SuppressWarnings("unused")
		RewardImpl toUpdate = null;
		try {
			Key k = KeyFactory.stringToKey(id);
			toUpdate = pm.getObjectById(RewardImpl.class, k);
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
