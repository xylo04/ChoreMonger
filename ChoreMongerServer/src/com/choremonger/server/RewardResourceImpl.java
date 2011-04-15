package com.choremonger.server;

import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Path("/reward")
public class RewardResourceImpl implements RewardResource {

	@Override
	public RewardImpl createReward(RewardImpl toCreate) {
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
	public void deleteReward(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		RewardImpl toDelete = null;
		try {
			Key k = KeyFactory.createKey(RewardImpl.class.getSimpleName(), id);
			toDelete = pm.getObjectById(RewardImpl.class, k);
			if (toDelete == null) {
				throw new WebApplicationException(404);
			}
			pm.deletePersistent(toDelete);
		} catch (JDOObjectNotFoundException e) {
			throw new WebApplicationException(404);
		} finally {
			pm.close();
		}

		return;
	}

	@Override
	public RewardImpl getReward(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		RewardImpl retval = null;
		try {
			Key k = KeyFactory.createKey(RewardImpl.class.getSimpleName(), id);
			retval = pm.getObjectById(RewardImpl.class, k);
		} catch (JDOObjectNotFoundException e) {
			throw new WebApplicationException(404);
		} finally {
			pm.close();
		}

		return retval;
	}

	@Override
	public RewardList getRewards() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		RewardList retval = new RewardList();
		try {
			Query query = pm.newQuery(RewardImpl.class);
			@SuppressWarnings("unchecked")
			List<RewardImpl> persistChoreList = (List<RewardImpl>) query
					.execute();
			retval.addAllRewards(persistChoreList);
		} catch (JDOObjectNotFoundException e) {
			throw new WebApplicationException(404);
		} finally {
			pm.close();
		}

		return retval;
	}

	@Override
	public void updateReward(String id, RewardImpl newValue) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		@SuppressWarnings("unused")
		RewardImpl toUpdate = null;
		try {
			Key k = KeyFactory.createKey(RewardImpl.class.getSimpleName(), id);
			toUpdate = pm.getObjectById(RewardImpl.class, k);
			toUpdate = newValue;
		} catch (JDOObjectNotFoundException e) {
			throw new WebApplicationException(404);
		} finally {
			pm.close();
		}

		return;
	}

}
