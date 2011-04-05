package com.choremonger.shared;

import java.util.Date;
import java.util.List;

public interface User {

	/**
	 * Add a {@link Chore} to this {@link User}. Also does the reverse.
	 * 
	 * @param toAdd
	 */
	public void addChore(Chore toAdd);

	/**
	 * Add reward points to this {@link User}'s balance.
	 * 
	 * @param amountToAdd
	 */
	public void addRewardPoints(double amountToAdd);

	/**
	 * Get the list of this {@link User}'s {@link Chore}s.
	 * 
	 * @return
	 */
	public List<Chore> getChores();

	/**
	 * Get this {@link User}'s date of birth.
	 * 
	 * @return
	 */
	public Date getDob();

	/**
	 * Get this {@link User}'s e-mail address.
	 * 
	 * @return
	 */
	public String getEmail();

	/**
	 * Get this {@link User}'s ID.
	 * 
	 * @return
	 */
	public String getId();

	/**
	 * Get this {@link User}'s name.
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Get this {@link User}'s reward point balance.
	 * 
	 * @return
	 */
	public double getRewardPoints();

	/**
	 * Redeem the given {@link Reward}. The {@link Reward}'s point value is
	 * subtracted from this {@link User}'s reward point balance and the
	 * redemption is logged.
	 * 
	 * @param toRedeem
	 */
	public void redeemReward(Reward toRedeem);

	/**
	 * Remove a {@link Chore} from this {@link User}. Also does the reverse.
	 * 
	 * @param toRemove
	 * @return
	 */
	public boolean removeChore(Chore toRemove);

	/**
	 * Set this {@link User}'s date of birth.
	 * 
	 * @param newDateOfBirth
	 */
	public void setDob(Date newDateOfBirth);

	/**
	 * Set this {@link User}'s e-mail address.
	 * 
	 * @param newEmail
	 */
	public void setEmail(String newEmail);

	/**
	 * Set this {@link User}'s ID.
	 * 
	 * @param newId
	 */
	public void setId(String newId);

	/**
	 * Set this {@link User}'s name.
	 * 
	 * @param newName
	 */
	public void setName(String newName);

	/**
	 * Subtract reward points from this {@link User}'s balance.
	 * 
	 * @param amountToSubtract
	 */
	public void subtractRewardPoints(double amountToSubtract);
}
