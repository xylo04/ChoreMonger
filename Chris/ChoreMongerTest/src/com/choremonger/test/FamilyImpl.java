package com.choremonger.test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import com.choremonger.shared.Chore;
import com.choremonger.shared.Family;
import com.choremonger.shared.Reward;
import com.choremonger.shared.User;

public class FamilyImpl implements Family {

	public static Family createFamily(String name) {
		Family retval = null;

		String xmlDocument = "<family><name>" + name + "</name></family>";

		HttpPost request = new HttpPost(HttpRequestExecutor.RESOURCE_ROOT
				+ "family");
		try {
			request.setEntity(new StringEntity(
					"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
							+ xmlDocument, "utf-8"));
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

		if (response.getStatusLine().getStatusCode() == 200) {
			retval = parseFamily(response, retval);
		}

		return retval;
	}

	public static void updateFamily(Family toUpdate) {

		String xmlDocument = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				+ "<family id=\""
				+ toUpdate.getId()
				+ "\"><name>"
				+ toUpdate.getName() + "</name></family>";

		HttpPost request = new HttpPost(HttpRequestExecutor.RESOURCE_ROOT
				+ "family/" + toUpdate.getId());
		try {
			request.setEntity(new StringEntity(xmlDocument, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setHeader("Content-Type", "application/xml");

		System.out
				.println("FamilyImpl is building request to update Family on the server");
		HttpResponse response = HttpRequestExecutor.executeRequest(request);
		if (response != null) {
			System.out.println("Got a response, code "
					+ response.getStatusLine().getStatusCode());
		}

		if (response.getStatusLine().getStatusCode() != 204) {
			System.out.println("That wasn't quite right!");
			System.exit(1);
		}
	}

	public static Family getFamily(String id) {
		Family retval = null;

		HttpGet request = new HttpGet(HttpRequestExecutor.RESOURCE_ROOT
				+ "family/" + id);

		System.out
				.println("FamilyImpl is building request to get Family from the server");
		HttpResponse response = HttpRequestExecutor.executeRequest(request);
		if (response != null) {
			System.out.println("Got a response, code "
					+ response.getStatusLine().getStatusCode());
		}

		if (response.getStatusLine().getStatusCode() == 200) {
			retval = parseFamily(response, retval);
		}

		return retval;
	}

	public static boolean deleteFamily(String id) {
		boolean retval = false;

		HttpDelete request = new HttpDelete(HttpRequestExecutor.RESOURCE_ROOT
				+ "family/" + id);

		System.out
				.println("FamilyImpl is building request to delete Family from the server");
		HttpResponse response = HttpRequestExecutor.executeRequest(request);
		if (response != null) {
			System.out.println("Got a response, code "
					+ response.getStatusLine().getStatusCode());
		}

		if (response.getStatusLine().getStatusCode() == 204) {
			retval = true;
		}

		return retval;
	}

	private static Family parseFamily(HttpResponse response, Family retval) {
		System.out.println("Going to try and parse out a Family");
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();
			FamilySaxHandler handler = new FamilySaxHandler();
			saxParser.parse(response.getEntity().getContent(), handler);
			retval = handler.getFamily();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retval;
	}

	private List<Chore> chores = new ArrayList<Chore>();
	private String id;

	private String name;

	private List<Reward> rewards = new ArrayList<Reward>();

	private List<User> users = new ArrayList<User>();

	public FamilyImpl() {
	}

	public FamilyImpl(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public void addChore(Chore toAdd) {
		chores.add(toAdd);
		updateFamily(this);
	}

	@Override
	public void addReward(Reward toAdd) {
		rewards.add(toAdd);
		updateFamily(this);
	}

	@Override
	public void addUser(User toAdd) {
		users.add(toAdd);
		updateFamily(this);
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
		boolean retval = chores.remove(toRemove);
		updateFamily(this);
		return retval;
	}

	@Override
	public boolean removeReward(Reward toRemove) {
		boolean retval = rewards.remove(toRemove);
		updateFamily(this);
		return retval;
	}

	@Override
	public boolean removeUser(User toRemove) {
		boolean retval = users.remove(toRemove);
		updateFamily(this);
		return retval;
	}

	@Override
	public void setId(String newId) {
		// no-op
	}

	@Override
	public void setName(String newName) {
		name = newName;
		updateFamily(this);
	}

}
