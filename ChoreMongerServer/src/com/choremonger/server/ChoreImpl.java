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
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;

import com.choremonger.shared.Chore;
import com.choremonger.shared.User;

@PersistenceCapable
@XmlRootElement(name = "chore")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChoreImpl implements Chore {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	@XmlAttribute
	@XmlID
	private String id;

	@Persistent
	@XmlElement
	private String instructions;

	@Persistent
	@XmlElement
	private String name;

	@Persistent
	@XmlElement
	private double pointValue;

	@Persistent
	@XmlElement
	private double priority;

	@Persistent
	@XmlElement
	private String status;

	@Persistent
	@XmlElement(name = "user")
	@XmlIDREF
	private List<UserImpl> users = new ArrayList<UserImpl>();

	@Override
	public void addUser(User toAdd) {
		((UserImpl) toAdd).addChore(this);
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getInstructions() {
		return instructions;
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
	public double getPriority() {
		return priority;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public List<User> getUsers() {
		List<User> retval = new ArrayList<User>();
		retval.addAll(users);
		return retval;
	}

	@Override
	public boolean removeUser(User toRemove) {
		return ((UserImpl) toRemove).removeChore(this);
	}

	@Override
	public void setId(String newId) {
		id = newId;
	}

	@Override
	public void setInstructions(String newInstructions) {
		instructions = newInstructions;
	}

	@Override
	public void setName(String newName) {
		name = newName;
	}

	@Override
	public void setPointValue(double newPointValue) {
		pointValue = newPointValue;
	}

	@Override
	public void setPriority(double newPriority) {
		priority = newPriority;
	}

	@Override
	public void setStatus(String newStatus) {
		status = newStatus;
	}

}
