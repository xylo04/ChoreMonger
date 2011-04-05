package com.choremonger.shared;

import java.util.Date;

public interface User {

	/**
	 * Add reward points to this {@link User}'s balance.
	 * 
	 * @param amountToAdd
	 */
	public void addRewardPoints(double amountToAdd);

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
