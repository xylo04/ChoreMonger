package com.choremonger.shared;

import java.util.List;

public interface Chore {

	/**
	 * Add a {@link User} to this {@link Chore}'s responsibility list.
	 * 
	 * @param toAdd
	 */
	public void addUser(User toAdd);

	/**
	 * Get this {@link Chore}'s ID.
	 * 
	 * @return
	 */
	public String getId();

	/**
	 * Get this {@link Chore}'s instructions.
	 * 
	 * @return
	 */
	public String getInstructions();

	/**
	 * Get this {@link Chore}'s name.
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Get this {@link Chore}'s reward point value.
	 * 
	 * @return
	 */
	public double getPointValue();

	/**
	 * Get the {@link User}s responsible for this {@link Chore}.
	 * 
	 * @return
	 */
	public List<User> getUsers();

	/**
	 * Remove a {@link User} from this {@link Chore}'s responsibility list.
	 * 
	 * @param toRemove
	 * @return whether the {@link User} was removed.
	 */
	public boolean removeUser(User toRemove);

	/**
	 * Set this {@link Chore}'s ID.
	 * 
	 * @param newId
	 */
	public void setId(String newId);

	/**
	 * Set this {@link Chore}'s instructions.
	 * 
	 * @param newInstructions
	 */
	public void setInstructions(String newInstructions);

	/**
	 * Set this {@link Chore}'s name.
	 * 
	 * @param newName
	 */
	public void setName(String newName);

	/**
	 * Set this {@link Chore}'s reward point value.
	 * 
	 * @param newPointValue
	 */
	public void setPointValue(double newPointValue);
}
