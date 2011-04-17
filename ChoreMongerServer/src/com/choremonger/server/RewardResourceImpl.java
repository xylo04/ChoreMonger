package com.choremonger.server;

import java.util.logging.Logger;

import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;

import com.choremonger.shared.Reward;

@Path("/reward")
public class RewardResourceImpl implements RewardResource {

	private Logger log = Logger.getLogger(RewardResourceImpl.class.getName());

	@Override
	public RewardImpl createReward(RewardImpl toCreate) {
		log.info("Create reward " + toCreate.getName());
		toCreate.setId(null);
		FamilyResource fr = new FamilyResourceImpl();
		FamilyImpl f = fr.getFamily();
		f.addReward(toCreate);
		fr.updateFamily(f.getId(), f);
		log.info("Reward got id " + toCreate.getId());
		return toCreate;
	}

	@Override
	public void deleteReward(String id) {
		log.info("Delete reward id " + id);
		boolean found = false;
		FamilyResource fr = new FamilyResourceImpl();
		FamilyImpl f = fr.getFamily();
		for (Reward r : f.getRewards()) {
			if (r.getId().equals(id)) {
				f.removeReward(r);
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
	public RewardImpl getReward(String id) {
		log.info("Retrieve reward id " + id);
		FamilyResource fr = new FamilyResourceImpl();
		FamilyImpl f = fr.getFamily();
		RewardImpl retval = null;
		for (Reward r : f.getRewards()) {
			if (r.getId().equals(id)) {
				retval = (RewardImpl) r;
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
	public RewardList getRewards() {
		log.info("Get rewards list");
		FamilyResource fr = new FamilyResourceImpl();
		FamilyImpl f = fr.getFamily();
		RewardList retval = new RewardList();
		for (Reward r : f.getRewards()) {
			retval.addReward((RewardImpl) r);
		}
		log.info("OK");
		return retval;
	}

	@Override
	public void updateReward(String id, RewardImpl newValue) {
		log.info("Update reward id " + id);
		if (!newValue.getId().equals(id)) {
			// uri and xml document didn't refer to the same ID
			throw new WebApplicationException(400);
		}
		FamilyResource fr = new FamilyResourceImpl();
		FamilyImpl f = fr.getFamily();
		RewardImpl toUpdate = null;
		for (Reward r : f.getRewards()) {
			if (r.getId().equals(id)) {
				toUpdate = (RewardImpl) r;
			}
		}
		if (toUpdate == null) {
			log.warning("Not found");
			throw new WebApplicationException(404);
		}
		f.removeReward(toUpdate);
		f.addReward(newValue);
		fr.updateFamily(f.getId(), f);
		log.info("OK");
	}
}
