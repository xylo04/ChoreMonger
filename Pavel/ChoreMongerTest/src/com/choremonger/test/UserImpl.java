package com.choremonger.test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;

import com.choremonger.shared.Chore;
import com.choremonger.shared.Family;
import com.choremonger.shared.Reward;
import com.choremonger.shared.User;

public class UserImpl implements User {

	public static User createUser() {
		User retval = null;

		HttpPost request = new HttpPost(HttpRequestExecutor.RESOURCE_ROOT
				+ "user");
		try {
			request
					.setEntity(new StringEntity(
							"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><user />",
							"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setHeader("Content-Type", "application/xml");

		System.out
				.println("UserImpl is building request for new User from the server");
		HttpResponse response = HttpRequestExecutor.executeRequest(request);
		if (response != null) {
			System.out.println("Got a response, code "
					+ response.getStatusLine().getStatusCode());
		}

		if (response.getStatusLine().getStatusCode() == 200) {
			retval = parseUser(response, retval);
		}

		// parse response XML into a UserImpl object
		// and return that
		return retval;
	}
	
	public static User getUserByName(String name) {
		User retval = null;
		List<User> temp = getUsersCollection();
		for (int i = 1; i < temp.size();i++) {
			if (temp.get(i).getName() == name) {
				retval = temp.get(i);
			}
		}
		
		return retval;
	}
	
	public static List<User> parseUsersCollection(HttpResponse response,List<User> retrievedUserLists){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
                SAXParser saxParser = factory.newSAXParser();
                UserSaxHandler handler = new UserSaxHandler();
                saxParser.parse(response.getEntity().getContent(), handler);
                retrievedUserLists = handler.getUsersCollections();
        }
        catch (Exception e) {
                e.printStackTrace();
        }
        return retrievedUserLists;
}

	public static List<User> getUsersCollection(){

        List<User> usersList=null;
        HttpGet request = new HttpGet(HttpRequestExecutor.RESOURCE_ROOT
                        + "/user/");
        HttpResponse response = HttpRequestExecutor.executeRequest(request);

        if (response.getStatusLine().getStatusCode() == 200) {
                usersList = parseUsersCollection(response, usersList);
        }
        return usersList;
	}


	public static User getUser(String id) {
		User retval = null;

		HttpGet request = new HttpGet(HttpRequestExecutor.RESOURCE_ROOT
				+ "user/" + id);

		System.out
				.println("UserImpl is building request to get User from the server");
		HttpResponse response = HttpRequestExecutor.executeRequest(request);
		if (response != null) {
			System.out.println("Got a response, code "
					+ response.getStatusLine().getStatusCode());
		}

		if (response.getStatusLine().getStatusCode() == 200) {
			retval = parseUser(response, retval);
		}

		// parse response XML into a UserImpl object
		// and return that
		return retval;
	}

	private static User parseUser(HttpResponse response, User retval) {
		System.out.println("Going to try and parse out a User");
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();
			UserSaxHandler handler = new UserSaxHandler();
			saxParser.parse(response.getEntity().getContent(), handler);
			retval = handler.getUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retval;
	}

	private List<Chore> chores = new ArrayList<Chore>();
	private String FamilyId;
	private String id;
	private double RewardPoints;
	private Date Dob;
	private String email;
	private String name;
	
	public Family getFamily() {
		Family temp = FamilyImpl.getFamily(FamilyId);
		return temp;
	}
	
	public void setFamily(Family newFamily) {
		FamilyId = newFamily.getId();
	}

	public UserImpl() {
		RewardPoints = 0;
		Dob = null;
		email = "";
		name = "";
	}
	
	public UserImpl(String n, double r, String e, Date d, String choreList, String f) {
		name = n;
		RewardPoints = r;
		email = e;
		Dob = d;
		FamilyId = f;
		if (choreList != "") {
			String[] temp = choreList.split("\\.");
			for(int i = 0; i < temp.length; i++){
				ChoreImpl temp_chore = new ChoreImpl(temp[i]);
				chores.add(temp_chore);
			}
		}
	}

	@Override
	public void setRewardPoints(double rewardPoints) {
		RewardPoints = rewardPoints;
		this.update();
	}
	
	@Override
	public void addChore(Chore toAdd) {
		chores.add(toAdd);
		this.update();
	}

	@Override
	public void addRewardPoints(double amountToAdd) {
		RewardPoints += amountToAdd;
		this.update();
	}

	@Override
	public List<Chore> getChores() {
		return chores;
	}

	@Override
	public Date getDob() {
		return Dob;
	}

	@Override
	public String getEmail() {
		return email;
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
	public double getRewardPoints() {
		return RewardPoints;
	}

	@Override
	public void redeemReward(Reward toRedeem) {
		if (toRedeem.getPointValue() <= RewardPoints) {
			RewardPoints = RewardPoints - toRedeem.getPointValue();
			toRedeem.redeemReward(this);
			this.update();
		}
	}

	@Override
	public boolean removeChore(Chore toRemove) {
		boolean success;
		success = chores.remove(toRemove);
		this.update();
		return success;
	}

	@Override
	public void setDob(Date newDateOfBirth) {
		Dob = newDateOfBirth;
		this.update();
	}

	@Override
	public void setEmail(String newEmail) {
		email = newEmail;
		this.update();
	}

	@Override
	public void setId(String newId) {
		id = newId;
		// send update to server
	}

	@Override
	public void setName(String newName) {
		name = newName;
		this.update();
	}

	@Override
	public void subtractRewardPoints(double amountToSubtract) {
		RewardPoints -= amountToSubtract;
		this.update();
	}

	public static void deleteUser(String id) {
		HttpDelete request = new HttpDelete(HttpRequestExecutor.RESOURCE_ROOT
				+ "user/" + id);
		HttpResponse response = HttpRequestExecutor.executeRequest(request);
		if (response != null) {
			System.out.println("Got a response, code "
					+ response.getStatusLine().getStatusCode());
		}
	}
	
	public void update() {
		String DobString = "";
		String ChoreString = "";
		if (chores.size() > 0) {
			ChoreString = "<chores>" + chores.get(0).getId();
			for (int i = 1; i < chores.size();i++) {
				ChoreString += "." + chores.get(i).getId();
			}
			ChoreString += "</chores>";
		}
		if (Dob != null) {
			DobString = Dob.toString();
		}
		HttpPut request = new HttpPut(HttpRequestExecutor.RESOURCE_ROOT
				+ "user/" + id);
		try {
			request.setEntity(new StringEntity(
					"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><user id=\""
							+ id + "\"><dob>" + DobString + "</dob><email>" + email + 
							"</email><name>" + name + "</name><rewardPoints>" + RewardPoints +
							"</rewardPoints>" + ChoreString + "<familyId>" + FamilyId +
							"</familyId></user>",
					"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setHeader("Content-Type", "application/xml");

		System.out
				.println("UserImpl is building request for updating User from the server");
		HttpResponse response = HttpRequestExecutor.executeRequest(request);
		if (response != null) {
			System.out.println("Got a response, code "
					+ response.getStatusLine().getStatusCode());
		}
	}

}
