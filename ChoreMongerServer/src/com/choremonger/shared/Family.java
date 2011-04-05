package com.choremonger.shared;

import java.util.List;

public interface Family {

	/**
	 * Add a {@link Chore} to this {@link Family}.
	 * 
	 * @param toAdd
	 */
	public void addChore(Chore toAdd);

	/**
	 * Add a {@link Reward} to this {@link Family}.
	 * 
	 * @param toAdd
	 */
	public void addReward(Reward toAdd);

	/**
	 * Add a {@link User} to this {@link Family}.
	 * 
	 * @param toAdd
	 */
	public void addUser(User toAdd);

	/**
	 * Get the {@link Chore}s associated with this {@link Family}.
	 * 
	 * @return
	 */
	public List<Chore> getChores();

	/**
	 * Get this {@link Family}'s ID.
	 * 
	 * @return
	 */
	public String getId();

	/**
	 * Get this {@link Family}'s name.
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Get the {@link Reward}s associated with this {@link Family}.
	 * 
	 * @return
	 */
	public List<Reward> getRewards();

	/**
	 * Get the {@link User}s associated with this {@link Family}.
	 * 
	 * @return
	 */
	public List<User> getUsers();

	/**
	 * Remove a {@link Chore} from this {@link Family}. This removes references
	 * from {@link User}s assigned to the {@link Chore}.
	 * 
	 * @param toRemove
	 * @return whether the {@link Chore} was removed.
	 */
	public boolean removeChore(Chore toRemove);

	/**
	 * Remove a {@link Reward} from this {@link Family}.
	 * 
	 * @param toRemove
	 * @return whether the {@link Reward} was removed.
	 */
	public boolean removeReward(Reward toRemove);

	/**
	 * Remove a {@link User} from this {@link Family}.
	 * 
	 * @param toRemove
	 * @return whether the {@link User} was removed.
	 */
	public boolean removeUser(User toRemove);

	/**
	 * Set this {@link Family}'s ID.
	 * 
	 * @param newId
	 */
	public void setId(String newId);

	/**
	 * Set this {@link Family}'s name.
	 * 
	 * @param newName
	 */
	public void setName(String newName);

}
