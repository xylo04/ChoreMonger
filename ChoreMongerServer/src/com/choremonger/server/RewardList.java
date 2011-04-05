package com.choremonger.server;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rewards")
@XmlAccessorType(XmlAccessType.FIELD)
public class RewardList {

	@XmlElement(name = "reward")
	private List<RewardImpl> rewards = new ArrayList<RewardImpl>();

	public void addAllRewards(List<RewardImpl> r) {
		rewards.addAll(r);
	}

	public void addReward(RewardImpl r) {
		rewards.add(r);
	}
}
