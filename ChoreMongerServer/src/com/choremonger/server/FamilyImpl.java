package com.choremonger.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

import com.choremonger.shared.Chore;
import com.choremonger.shared.Family;
import com.choremonger.shared.Reward;
import com.choremonger.shared.User;

@PersistenceCapable
@XmlRootElement(name = "family")
@XmlAccessorType(XmlAccessType.FIELD)
public class FamilyImpl implements Family {

	@Persistent
	@XmlElement(name = "chore")
	private List<ChoreImpl> chores = new ArrayList<ChoreImpl>();

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	@XmlAttribute
	@XmlID
	private String id;

	@Persistent
	@XmlElement
	private String name;

	@Persistent
	@XmlElement(name = "reward")
	private List<RewardImpl> rewards = new ArrayList<RewardImpl>();

	@Persistent
	@XmlElement(name = "user")
	private List<UserImpl> users = new ArrayList<UserImpl>();

	@Override
	public void addChore(Chore toAdd) {
		chores.add((ChoreImpl) toAdd);
	}

	@Override
	public void addReward(Reward toAdd) {
		rewards.add((RewardImpl) toAdd);
	}

	@Override
	public void addUser(User toAdd) {
		users.add((UserImpl) toAdd);
	}

	@Override
	public List<Chore> getChores() {
		List<Chore> retval = new ArrayList<Chore>();
		retval.addAll(chores);
		return retval;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<Reward> getRewards() {
		List<Reward> retval = new ArrayList<Reward>();
		retval.addAll(rewards);
		return retval;
	}

	@Override
	public List<User> getUsers() {
		List<User> retval = new ArrayList<User>();
		retval.addAll(users);
		return retval;
	}

	@Override
	public boolean removeChore(Chore toRemove) {
		return chores.remove(toRemove);
	}

	@Override
	public boolean removeReward(Reward toRemove) {
		return rewards.remove(toRemove);
	}

	@Override
	public boolean removeUser(User toRemove) {
		return users.remove(toRemove);
	}

	@Override
	public void setId(String newId) {
		id = newId;
	}

	@Override
	public void setName(String newName) {
		name = newName;
	}

}
