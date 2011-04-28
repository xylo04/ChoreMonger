package com.android.chores;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;

import android.util.Log;

import com.choremonger.shared.Chore;
import com.choremonger.shared.User;

public class ChoreImpl implements Chore
{
	private static final String TAG=ChoreImpl.class.getName();
	List<User> users_assigned = new ArrayList<User>();
	String id;
	String intsructions;
	String name;
	double points;
	String status;
	double priority;
	String str;
	String list_of_users;

	public ChoreImpl()
	{
		this(null, null, 0.0, null, null, 0.0);
	}
	public ChoreImpl(String name, String id, double points)
	{
		this(name, id, points, null, null, 0.0);
	}
	public ChoreImpl(String name, String id, double points, List<User> users)
	{
		this(name, id, points, users, null, 0.0);
	}
	public ChoreImpl(String name, String id, double points, List<User> users, String intsructions)
	{
		this(name, id, points, users, intsructions, 0.0);
	}
	public ChoreImpl(String name, String id, double points, List<User> users, String intsructions, double priority)
	{
		this.id = id;
		this.name = name;
		this.users_assigned = users;
		this.points = points;
		this.intsructions = intsructions;
		this.priority = priority;
		this.status = "Not Started";
		this.str = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><chore id=\"\"><instructions>"+this.intsructions+"</instructions><name>"+this.name+"</name><pointValue>"+Double.toString(this.points)+"</pointValue>";
		this.str += "<users>";
		if (this.users_assigned != null)
		{
			for  (int i = 0; i < this.users_assigned.size(); i++)
			{ 
				String temp = this.users_assigned.get(i).getId();
				list_of_users += temp + ".";
			}
		}
		else
		{
			this.users_assigned = new ArrayList<User>();
			this.list_of_users = null;
		}
		this.str += this.list_of_users;
		this.str += "</users>";
		this.str+="<priority>"+Double.toString(this.priority)+"</priority><status>"+this.status+"</status></chore>";
		Log.d(TAG,this.str);
		HttpPost request = new HttpPost(HttpRequestExecutor.RESOURCE_ROOT + "/chore/");
		try {
			request.setEntity(new StringEntity(this.str));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setHeader("Content-Type", "application/xml");

		HttpResponse response = HttpRequestExecutor.executeRequest(request);
		try{
			BufferedReader BR = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			this.str = BR.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.id = str.substring(str.indexOf("<chore id=\"")+11, str.indexOf("\"><instru"));						//name
		HttpPut Request = new HttpPut(HttpRequestExecutor.RESOURCE_ROOT + "/chore/" + this.id);
		try {
			Request.setEntity(new StringEntity(this.str));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Request.setHeader("Content-Type", "application/xml");
		HttpRequestExecutor.executeRequest(Request);

	}
	public ChoreImpl(String id)
	{
		this.id = id;
		HttpGet request = new HttpGet(HttpRequestExecutor.RESOURCE_ROOT + "/chore/" + this.id);
		HttpResponse response = HttpRequestExecutor.executeRequest(request);


		if (response.getStatusLine().getStatusCode() == 200)
		{
			try {
				BufferedReader BR = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				this.str = BR.readLine();
				Log.d(TAG,this.str);
				String str = this.str;
				if ((str.indexOf("<name>") != -1))
					this.name = str.substring(str.indexOf("<name>")+6, str.indexOf("</name>"));						//name
				else 
					this.name = "";
				if ((str.indexOf("<instructions>") != -1))
					this.intsructions = str.substring(str.indexOf("<instructions>")+14, str.indexOf("</instructions>"));			//instructions
				else 
					this.intsructions = "";
				if ((str.indexOf("<status>") != -1))
					this.status = str.substring(str.indexOf("<status>")+8, str.indexOf("</status>"));					//status
				else 
					this.status = "";
				if ((str.indexOf("<pointValue>") != -1))
					this.points = Double.parseDouble(str.substring(str.indexOf("<pointValue>")+12, str.indexOf("</pointValue>")));			//points
				else 
					this.points = 0.0;
				if ((str.indexOf("<priority>") != -1))
					this.priority = Double.parseDouble(str.substring(str.indexOf("<priority>")+10, str.indexOf("</priority>")));		//priority
				else 
					this.priority = 0.0;
				//users
				if(str.indexOf("<users>") != -1)
				{
					this.list_of_users = str.substring(str.indexOf("<users>")+7, str.indexOf("</users>"));
				}
				//users
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (response.getStatusLine().getStatusCode() == 404)
		{
			//does not exist
		}
		else if (response.getStatusLine().getStatusCode() == 403)
		{
			//not allowed acess
		}
		else if (response.getStatusLine().getStatusCode() == 401)
		{
			//not logged in
		}

	}

	public String getStatus()
	{
		return this.status;
	}
	public void setStatus(String status)
	{
		this.str = str.replace(this.status, status);
		this.status = status;
		HttpPut request = new HttpPut(HttpRequestExecutor.RESOURCE_ROOT + "/chore/" + this.id);
		try {
			request.setEntity(new StringEntity(this.str));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setHeader("Content-Type", "application/xml");
		HttpRequestExecutor.executeRequest(request);
	}
	public double getPriority()
	{
		return this.priority;
	}
	public void setPriority(double priority)
	{
		this.str = str.replace("<priority>"+Double.toString(this.priority), "<priority>"+Double.toString(priority));
		this.priority = priority;
		HttpPut request = new HttpPut(HttpRequestExecutor.RESOURCE_ROOT + "/chore/" + this.id);
		try {
			request.setEntity(new StringEntity(this.str));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setHeader("Content-Type", "application/xml");
		HttpRequestExecutor.executeRequest(request);

	}

/**/	public void addUser(User toAdd)
	{
		String[] temp;
		if(this.list_of_users == null){this.list_of_users = "";}
		this.list_of_users += toAdd.getId() + ".";
		temp = this.str.split("<users>");
		this.str = temp[0] + "<users>"+this.list_of_users+temp[1];
		Log.d(TAG,this.str);
		HttpPut request = new HttpPut(HttpRequestExecutor.RESOURCE_ROOT + "/chore/" + this.id);
		try {
			request.setEntity(new StringEntity(this.str));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setHeader("Content-Type", "application/xml");
		HttpRequestExecutor.executeRequest(request);


	}
	public String getId()
	{
		return this.id;
	}
	public String getInstructions()
	{
		return this.intsructions;
	}
	public String getName()
	{
		return this.name;
	}
	public double getPointValue()
	{
		return this.points;
	}
	public List<User> getUsers()
	{
		String[] temp;
		temp = this.list_of_users.split("\\.");
		for (int i = 0; i<temp.length-1; i++)
		{
			if (temp[i] != null)
			{
				Log.d(TAG,"***************"+temp[i]+"*************");
				this.users_assigned.add(UserImpl.getUser(temp[i]));
			}
		}
		return this.users_assigned;
	}

/**/	public boolean removeUser(User toRemove)
	{
		this.str = str.replace(toRemove.getId()+".", "");
		HttpPut request = new HttpPut(HttpRequestExecutor.RESOURCE_ROOT + "/chore/" + this.id);
		try {
			request.setEntity(new StringEntity(this.str));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setHeader("Content-Type", "application/xml");
		HttpRequestExecutor.executeRequest(request);
		return this.users_assigned.remove(toRemove);
	}

	public void setId(String newId)
	{
	}
	public void setInstructions(String newInstructions)
	{
		this.str = str.replace("<instructions>"+this.intsructions, "<instructions>"+newInstructions);
		this.intsructions = newInstructions;
		HttpPut request = new HttpPut(HttpRequestExecutor.RESOURCE_ROOT + "/chore/" + this.id);
		try {
			request.setEntity(new StringEntity(this.str));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setHeader("Content-Type", "application/xml");
		HttpRequestExecutor.executeRequest(request);
	}
	public void setName(String newName)
	{
		this.str = str.replace("<name>"+this.name, "<name>"+newName);
		this.name = newName;
		HttpPut request = new HttpPut(HttpRequestExecutor.RESOURCE_ROOT + "/chore/" + this.id);
		try {
			request.setEntity(new StringEntity(this.str));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setHeader("Content-Type", "application/xml");
		HttpRequestExecutor.executeRequest(request);
	}
	public void setPointValue(double newPointValue)
	{
		this.str = str.replace("<pointValue>"+this.points, "<pointValue>"+Double.toString(newPointValue));
		this.points = newPointValue;
		HttpPut request = new HttpPut(HttpRequestExecutor.RESOURCE_ROOT + "/chore/" + this.id);
		try {
			request.setEntity(new StringEntity(this.str));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setHeader("Content-Type", "application/xml");
		HttpRequestExecutor.executeRequest(request);
	}
	public static void deleteChore(String id)
	{
		HttpDelete request = new HttpDelete(HttpRequestExecutor.RESOURCE_ROOT + "/chore/" + id);
		HttpRequestExecutor.executeRequest(request);
	}
}