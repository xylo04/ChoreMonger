package com.android.chores;


import java.io.UnsupportedEncodingException;

import java.util.List;

import java.io.UnsupportedEncodingException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpResponse;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;

import android.util.Log;

import com.choremonger.shared.Reward;
import com.choremonger.shared.User;

public class RewardImpl implements Reward {

	private static final String TAG =RewardImpl.class.getName();


	private String id;
	private String description;
	private String rewardName;
	private double pointsValue;
	private boolean isOneTimeReward;

	
	
	public RewardImpl(){
	}

	public RewardImpl(String id, String description, String rewardName,
			double pointsValue, boolean isOneTimeReward) {
		this.id = id;
		this.description = description;
		this.rewardName = rewardName;
		this.pointsValue = pointsValue;
		this.isOneTimeReward = isOneTimeReward;
	}


	public static Reward createReward(Reward myreward) {


		Reward retrievedReward = null;

		HttpPost request = new HttpPost(HttpRequestExecutor.RESOURCE_ROOT
				+ "/reward");
		try {
			String rewardXML = "<reward><description>"
					+ myreward.getDescription() + "</description>"
					+ "<isOneTime>" + Boolean.toString(myreward.isOneTime())
					+ "</isOneTime>" + "<name>" + myreward.getName()
					+ "</name>" + "<pointValue>"
					+ Double.toString(myreward.getPointValue())
					+ "</pointValue></reward>";
			request.setEntity(new StringEntity(
					"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
							+ rewardXML, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setHeader("Content-Type", "application/xml");

		Log.d(TAG,"RewardImpl is building request for new Reward from the server");
		HttpResponse response = HttpRequestExecutor.executeRequest(request);
		if (response != null) {
			Log.d(TAG,"Got a response, code "
					+ response.getStatusLine().getStatusCode());
		} else {
			Log.e(TAG, "Response was null, something is wrong...");
			return null;
		}

		if (response.getStatusLine().getStatusCode() == 200) {
			retrievedReward = parseReward(response, retrievedReward);
		}

		// parse response XML into a FamilyImpl object
		// and return that
		return retrievedReward;
	}

	public static Reward updateReward(Reward myreward) {

		Reward retrievedReward = null;

		HttpPut request = new HttpPut(HttpRequestExecutor.RESOURCE_ROOT
				+ "/reward/" + myreward.getId());
		try {
			String rewardXML = "<reward id=\"" + myreward.getId()
					+ "\">><description>" + myreward.getDescription()
					+ "</description>" + "<isOneTime>"
					+ Boolean.toString(myreward.isOneTime()) + "</isOneTime>"
					+ "<name>" + myreward.getName() + "</name>"
					+ "<pointValue>"
					+ Double.toString(myreward.getPointValue())
					+ "</pointValue></reward>";
			request.setEntity(new StringEntity(
					"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
							+ rewardXML, "utf-8"));

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setHeader("Content-Type", "application/xml");

		Log.d(TAG,"RewardImpl is building request for new Reward from the server");
		HttpResponse response = HttpRequestExecutor.executeRequest(request);
		if (response != null) {
			System.out.println("Got a response, code "
					+ response.getStatusLine().getStatusCode());
		}

		if (response.getStatusLine().getStatusCode() == 200) {
			retrievedReward = parseReward(response, retrievedReward);
		}

		// parse response XML into a FamilyImpl object
		// and return that
		return retrievedReward;
	}

	
public static void deleteReward(String id){
	
	HttpDelete request = new HttpDelete(HttpRequestExecutor.RESOURCE_ROOT
			+ "/reward/"+id);
	HttpResponse response = HttpRequestExecutor.executeRequest(request);
	
	if (response != null) {
		Log.d(TAG,"Deleted "
				+ response.getStatusLine().getStatusCode());
	}
	if (response.getStatusLine().getStatusCode() == 204) {
		Log.d(TAG,"Got a response, Succeessfully Deleted");
	}
}


	public static Reward parseReward(HttpResponse response,
			Reward retrievedReward) {
		System.out.println("Going to try and parse out a Reward");
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();
			RewardSaxHandler handler = new RewardSaxHandler();
			saxParser.parse(response.getEntity().getContent(), handler);
			retrievedReward = handler.getReward();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retrievedReward;
	}

	public static List<Reward> parseRewardsCollection(HttpResponse response,List<Reward> retrievedRewardLists){
		Log.d(TAG,"Going to try and parse lists of Rewards");
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();
			RewardSaxHandler handler = new RewardSaxHandler();
			saxParser.parse(response.getEntity().getContent(), handler);
			retrievedRewardLists = handler.getRewardsCollections();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return retrievedRewardLists;
	}
	

	public static Reward getReward(String id) {

		Reward retrievedReward = null;
		HttpGet request = new HttpGet(HttpRequestExecutor.RESOURCE_ROOT
				+ "/reward/" + id);
		System.out
				.println("RewardImpl is building request to get Reward from the server");

		HttpResponse response = HttpRequestExecutor.executeRequest(request);

		if (response != null) {

			Log.d(TAG,"Got a response, code "
			+ response.getStatusLine().getStatusCode());

			System.out.println("Got a response, code "
					+ response.getStatusLine().getStatusCode());

		}

		if (response.getStatusLine().getStatusCode() == 200) {
			retrievedReward = parseReward(response, retrievedReward);
		}

		// parse response XML into a FamilyImpl object
		// and return that
		return retrievedReward;

}
	public List<Reward> getRewardsCollection(){
		
		List<Reward> rewardsList=null;
		HttpGet request = new HttpGet(HttpRequestExecutor.RESOURCE_ROOT
				+ "/reward/");
		Log.d(TAG,"RewardImpl is building request to get Reward from the server");
		HttpResponse response = HttpRequestExecutor.executeRequest(request);
		
		if (response != null) {
			Log.d(TAG,"Got a response, code "
			+ response.getStatusLine().getStatusCode());
		}
		
		if (response.getStatusLine().getStatusCode() == 200) {
			rewardsList = parseRewardsCollection(response, rewardsList);
		}
		
		// parse response XML into a FamilyImpl object
		// 	and return that
		
		return rewardsList;
		
	}

	public String getDescription() {
		return description;
	}

	/**
	 * Get this {@link Reward}'s ID.
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Get this {@link Reward}'s name.
	 * 
	 * @return
	 */
	public String getName() {
		return rewardName;
	}

	/**
	 * Get this {@link Reward}'s point value.
	 * 
	 * @return
	 */
	public double getPointValue() {
		return pointsValue;
	}

	/**
	 * Get whether this {@link Reward} is "one time" or not. If it is a one time
	 * reward, then it will be removed after it is redeemed.
	 * 
	 * @return
	 */
	public boolean isOneTime() {
		// TODO
		return isOneTimeReward;
	}

	/**
	 * Redeem this {@link Reward} for the given {@link User}. The point value of
	 * this {@link Reward} is subtracted from the {@link User}. A redemption is
	 * logged. If the {@link Reward} is set to be "one time," then it is
	 * removed.
	 * 
	 * @param isRedeeming
	 */

	public void redeemReward(User isRedeeming) {

	}

	/**
	 * Set this {@link Reward}'s description.
	 * 
	 * @param newDescription
	 */
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}

	/**
	 * Set this {@link Reward}'s ID.
	 * 
	 * @param newId
	 */
	public void setId(String newId) {
		this.id = newId;
	}

	/**
	 * Set this {@link Reward}'s name.
	 * 
	 * @param newName
	 */
	public void setName(String newName) {
		this.rewardName = newName;
	}

	/**
	 * Set whether this {@link Reward} is "one time" or not. If it is a one time
	 * reward, then it will be removed after it is redeemed.
	 * 
	 * @param isOneTime
	 */

	public void setOneTime(boolean isOneTime) {
		this.isOneTimeReward = isOneTime;
	}

	/**
	 * Set this {@link Reward}'s point value.
	 * 
	 * @param newPointValue
	 */

	public void setPointValue(double newPointValue) {
		this.pointsValue = newPointValue;
	}
}