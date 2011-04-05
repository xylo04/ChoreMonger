package com.choremonger.shared;

public interface Reward {

	/**
	 * Get this {@link Reward}'s description.
	 * 
	 * @return
	 */
	public String getDescription();

	/**
	 * Get this {@link Reward}'s ID.
	 * 
	 * @return
	 */
	public String getId();

	/**
	 * Get this {@link Reward}'s name.
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Get this {@link Reward}'s point value.
	 * 
	 * @return
	 */
	public double getPointValue();

	/**
	 * Get whether this {@link Reward} is "one time" or not. If it is a one time
	 * reward, then it will be removed after it is redeemed.
	 * 
	 * @return
	 */
	public boolean isOneTime();

	/**
	 * Redeem this {@link Reward} for the given {@link User}. The point value of
	 * this {@link Reward} is subtracted from the {@link User}. A redemption is
	 * logged. If the {@link Reward} is set to be "one time," then it is
	 * removed.
	 * 
	 * @param isRedeeming
	 */
	public void redeemReward(User isRedeeming);

	/**
	 * Set this {@link Reward}'s description.
	 * 
	 * @param newDescription
	 */
	public void setDescription(String newDescription);

	/**
	 * Set this {@link Reward}'s ID.
	 * 
	 * @param newId
	 */
	public void setId(String newId);

	/**
	 * Set this {@link Reward}'s name.
	 * 
	 * @param newName
	 */
	public void setName(String newName);

	/**
	 * Set whether this {@link Reward} is "one time" or not. If it is a one time
	 * reward, then it will be removed after it is redeemed.
	 * 
	 * @param isOneTime
	 */
	public void setOneTime(boolean isOneTime);

	/**
	 * Set this {@link Reward}'s point value.
	 * 
	 * @param newPointValue
	 */
	public void setPointValue(double newPointValue);
}
