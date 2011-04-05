package com.choremonger.test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import com.choremonger.shared.Chore;
import com.choremonger.shared.Family;
import com.choremonger.shared.Reward;
import com.choremonger.shared.User;

public class FamilyImpl implements Family {

	private List<Chore> chores = new ArrayList<Chore>();
	private List<Reward> rewards = new ArrayList<Reward>();
	private List<User> users = new ArrayList<User>();
	private String id;
	private String name;

	private FamilyImpl() {
	}

	public static Family createFamily() {
		HttpPost request = new HttpPost(HttpRequestExecutor.RESOURCE_ROOT
				+ "family");
		try {
			request.setEntity(new StringEntity(
					"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><family />",
					"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setHeader("Content-Type", "application/xml");

		System.out
				.println("FamilyImpl is building request for new Family from the server");
		HttpResponse response = HttpRequestExecutor.executeRequest(request);
		if (response != null) {
			System.out.println("Got a response, code "
					+ response.getStatusLine().getStatusCode());
		}

		// parse response XML into a FamilyImpl object
		// and return that
		return null;
	}

	@Override
	public void addChore(Chore toAdd) {
		chores.add(toAdd);
		// send update to server
	}

	@Override
	public void addReward(Reward toAdd) {
		rewards.add(toAdd);
		// send update to server
	}

	@Override
	public void addUser(User toAdd) {
		users.add(toAdd);
		// send update to server
	}

	@Override
	public List<Chore> getChores() {
		return chores;
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
	public List<Reward> getRewards() {
		return rewards;
	}

	@Override
	public List<User> getUsers() {
		return users;
	}

	@Override
	public boolean removeChore(Chore toRemove) {
		return chores.remove(toRemove);
		// send update to server
	}

	@Override
	public boolean removeReward(Reward toRemove) {
		return rewards.remove(toRemove);
		// send update to server
	}

	@Override
	public boolean removeUser(User toRemove) {
		return users.remove(toRemove);
		// send update to server
	}

	@Override
	public void setId(String newId) {
		id = newId;
		// send update to server
	}

	@Override
	public void setName(String newName) {
		name = newName;
		// send update to server
	}

}
