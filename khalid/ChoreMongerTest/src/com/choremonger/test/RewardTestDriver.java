package com.choremonger.test;

import com.choremonger.shared.*;

public class RewardTestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Retrieving a reward
		System.out.println("Now I'm trying to get a reward");
		Reward reward=RewardImpl.getReward("agtjaG9yZW1vbmdlcnIiCxIKRmFtaWx5SW1wbBjxLgwLEgpSZXdhcmRJbXBsGLkXDA");
		if (reward==null){
			System.out.println("Sorry! No rewards this time!");
			System.exit(1);
		}
		System.out.println("Reward Found");
		System.out.println(reward.getName());
		System.out.println(reward.getId());
		
	
		
		// Creating a reward
		
		/*Reward myreward=new RewardImpl("23", "$25 Amazon.com Gift Card", "Amazon Gift Card", 100, true);
		Reward createdReward=RewardImpl.createReward(myreward);
		if (createdReward==null){
			System.out.println("Oooops! No rewards created!");
			System.exit(1);
		}
		System.out.println("Reward Created");
		System.out.println(createdReward.getName());
		System.out.println(createdReward.getId());
		*/
		/*Reward myupdatedReward=new RewardImpl("agtjaG9yZW1vbmdlcnIiCxIKRmFtaWx5SW1wbBjxLgwLEgpSZXdhcmRJbXBsGLsXDA",
				"$200 Amazon.com Gift Card", "Amazon Gift Card", 300, true);
		Reward myRetrievedReward=RewardImpl.updateReward(myupdatedReward);
		
		if (myRetrievedReward==null){
			System.out.println("Oooops! Unable to update reward!");
			System.exit(1);
		}
		
		System.out.println("Reward updated");
		System.out.println(myRetrievedReward.getName());
		System.out.println(myRetrievedReward.getId());
		*/
		
		//RewardImpl.deleteReward("agtjaG9yZW1vbmdlcnIiCxIKRmFtaWx5SW1wbBjxLgwLEgpSZXdhcmRJbXBsGOkHDA");
	}
}
