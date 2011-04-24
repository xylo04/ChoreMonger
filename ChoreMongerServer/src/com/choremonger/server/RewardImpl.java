package com.choremonger.server;

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

import com.choremonger.shared.Reward;
import com.choremonger.shared.User;

@PersistenceCapable
@XmlRootElement(name = "reward")
@XmlAccessorType(XmlAccessType.FIELD)
public class RewardImpl implements Reward {

	@Persistent
	@XmlElement
	private String description;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	@XmlAttribute
	@XmlID
	private String id;

	@Persistent
	@XmlElement
	private boolean isOneTime;

	@Persistent
	@XmlElement
	private String name;

	@Persistent
	@XmlElement
	private double pointValue;

	@SuppressWarnings("unused")
	@Persistent
	@XmlElement
	private String users;

	@Override
	public String getDescription() {
		return description;
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
	public double getPointValue() {
		return pointValue;
	}

	@Override
	public boolean isOneTime() {
		return isOneTime;
	}

	@Override
	public void redeemReward(User isRedeeming) {
		// TODO Do this!
	}

	@Override
	public void setDescription(String newDescription) {
		description = newDescription;
	}

	@Override
	public void setId(String newId) {
		id = newId;
	}

	@Override
	public void setName(String newName) {
		name = newName;
	}

	@Override
	public void setOneTime(boolean isOneTime) {
		this.isOneTime = isOneTime;
	}

	@Override
	public void setPointValue(double newPointValue) {
		pointValue = newPointValue;
	}

}
