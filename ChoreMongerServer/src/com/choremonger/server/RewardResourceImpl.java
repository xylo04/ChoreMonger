package com.choremonger.server;

import javax.ws.rs.Path;

import com.choremonger.shared.Reward;

@Path("/reward")
public class RewardResourceImpl implements RewardResource {

	@Override
	public Reward createReward(Reward reward) {
		return reward;
	}

	@Override
	public void deleteReward(String id) {
		return;
	}

	@Override
	public Reward getReward(String id) {
		RewardImpl retval = new RewardImpl();
		retval.setId(id);
		return retval;
	}

	@Override
	public RewardList getRewards() {
		RewardList retval = new RewardList();
		retval.addReward(new RewardImpl());
		retval.addReward(new RewardImpl());
		return retval;
	}

	@Override
	public void updateReward(String id, Reward reward) {
		return;
	}

}
