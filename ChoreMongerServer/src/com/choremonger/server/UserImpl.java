package com.choremonger.server;

import java.util.Date;
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
import com.choremonger.shared.Reward;
import com.choremonger.shared.User;

@PersistenceCapable
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserImpl implements User {

	@SuppressWarnings("unused")
	@Persistent
	@XmlElement
	private String chores;

	@Persistent
	@XmlElement
	private Date dob;

	@Persistent
	@XmlElement
	private String email;

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
	@XmlElement
	private double rewardPoints;

	@SuppressWarnings("unused")
	@Persistent
	@XmlElement
	private String familyId;

	@Override
	public void addChore(Chore toAdd) {
		// chores.add((ChoreImpl) toAdd);
		// toAdd.getUsers().add(this);
	}

	@Override
	public void addRewardPoints(double amountToAdd) {
		rewardPoints += amountToAdd;
	}

	@Override
	public List<Chore> getChores() {
		// List<Chore> retval = new ArrayList<Chore>();
		// retval.addAll(chores);
		// return retval;
		return null;
	}

	@Override
	public Date getDob() {
		return dob;
	}

	@Override
	public String getEmail() {
		return email;
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
	public double getRewardPoints() {
		return rewardPoints;
	}

	@Override
	public void redeemReward(Reward toRedeem) {
		// TODO Do this!
	}

	@Override
	public boolean removeChore(Chore toRemove) {
		// toRemove.getUsers().remove(this);
		// return chores.remove(toRemove);
		return true;
	}

	@Override
	public void setDob(Date newDateOfBirth) {
		dob = newDateOfBirth;
	}

	@Override
	public void setEmail(String newEmail) {
		email = newEmail;
	}

	@Override
	public void setId(String newId) {
		id = null;
	}

	@Override
	public void setName(String newName) {
		name = newName;
	}

	@Override
	public void subtractRewardPoints(double amountToSubtract) {
		rewardPoints -= amountToSubtract;
	}

	public void setRewardPoints(double rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

}
