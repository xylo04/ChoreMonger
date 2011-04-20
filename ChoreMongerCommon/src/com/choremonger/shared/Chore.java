package com.choremonger.shared;

import java.util.List;

public interface Chore {

	// string status, double priority

	public void addUser(User toAdd);
	public String getId();
	public String getInstructions();
	public String getName();
	public double getPointValue();
	public double getPriority();
	public String getStatus();
	public List<User> getUsers();
	public boolean removeUser(User toRemove);
	public void setId(String newId);
	public void setInstructions(String newInstructions);
	public void setName(String newName);
	public void setPointValue(double newPointValue);
	public void setPriority(double newPriority);
	public void setStatus(String newStatus);
}
