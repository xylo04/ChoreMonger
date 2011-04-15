package com.choremonger.shared;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;

import com.choremonger.shared.Chore;
import com.choremonger.shared.Family;
import com.choremonger.shared.Reward;
import com.choremonger.shared.User;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;





public class Chore implements Chore
{
	List<User> users_assigned;
	String id;
	String intsructions;
	String name;
	double points;
	String status;
	double priority;
	String str;

	public Chore()
	{
		this(null, null, null, 0.0, null, 0.0);
	}
	public Chore(String name, String id, List<User> users)
	{
		this(name, id, users, 0.0, null, 0.0);
	}
	public Chore(String name, String id, List<User> users, double points)
	{
		this(name, id, users, points, null, 0.0);
	}
	public Chore(String name, String id, List<User> users, double points, String intsructions)
	{
		this(name, id, users, points, intsructions, 0.0);
	}
	public Chore(String name, String id, List<User> users, double points, String intsructions, double priority)
	{
		Iterator itr = users.iterator();

		this.id = id;
		this.name = name;
		this.users_assigned = users;
		this.points = points;
		this.intsructions = intsructions;
		this.priority = priority;
		this.status = "Not Started";
		str = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><chore id=\"\"><instructions>"+this.intsructions+"</instructions><name>"+this.name+"</name><pointValue>"+Double.toString(this.points)+"</pointValue>
		for (users.hasNext())
		{ 
			String temp = Double.toString(itr.next().getId());
			str.concat("<users><user id=").concat(temp).concast(" uri=\”/user/").concat(temp).concat("\” />");
		}
		str.concat("</users><priority>"+Double.toString(this.priority)+"</priority><status>"+this.status+"</status></chore>");
		HttpPost request = new HttpPost(HttpRequestExecutor.RESOURCE_ROOT + "chore/", str);
		HttpResponse response = HttpRequestExecutor.executeRequest(request);
		this.id = response.getEntity().getContent();


	}
	public Chore(String id)
	{
		String value;
		this.id = id;
		HttpGet request = new HttpGet(HttpRequestExecutor.RESOURCE_ROOT + "chore/" + this.id);
		HttpResponse response = HttpRequestExecutor.executeRequest(request);


		if (response.getStatusLine().getStatusCode() == 200)
		{
			try {
				this.str = response.getEntity().getContent();
				if ((str.indexOf("<name>") != -1)
					this.name = str.substring(str.indexOf("<name>")+6, str.indexOf("</name>"));						//name
				else 
					this.name = "";
				if ((str.indexOf("<intsructions>") != -1)
					this.intsructions = str.substring(str.indexOf("<intsructions>")+14, str.indexOf("</intsructions>"));			//instructions
				else 
					this.intsructions = "";
				if ((str.indexOf("<status>") != -1)
					this.status = str.substring(str.indexOf("<status>")+8, str.indexOf("</status>"));					//status
				else 
					this.status = "";
				if ((str.indexOf("<points>") != -1)
					this.points = Double.parseDouble(str.substring(str.indexOf("<points>")+8, str.indexOf("</points>")));			//points
				else 
					this.points = 0.0;
				if ((str.indexOf("<priority>") != -1)
					this.priority = Double.parseDouble(str.substring(str.indexOf("<priority>")+10, str.indexOf("</priority>")));		//priority
				else 
					this.priority = 0.0;
				//users
				for (int i = 0; i>-5; i++)
				{
					value = str.substring(str.indexOf("<user id=")+9, str.indexOf(" uri="));
					str = str.substring(str.indexOf(" uri="));
					this.users_assigned.add(getUser(value));
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
		if (this.status  = "Completed")
		{
			Iterator itr = List.iterator();
			while(itr.hasNext())
				//itr.give_reward(this.points);
		}

	}
	public String getPriority()
	{
		return this.priority;
	}
	public void setPriority(double priority)
	{
		this.str = str.replace(this.priority, Double.toString(priority));
		this.priority = priority;
		HttpPut request = new HttpPut(HttpRequestExecutor.RESOURCE_ROOT + "chore/" + this.id, str);
		HttpResponse response = HttpRequestExecutor.executeRequest(request);
	}

/**/	public void addUser(User toAdd)
	{
		String[] temp;
		this.users_assigned.add(toAdd);	
		temp = this.str.split("<users>");
		this.str = temp[0].concat("<users>").concat("<user id=\""+toAdd.getId()+"\" uri=\"/user/"+toAdd.getId()+"\">").concat(temp[1]);
		HttpPut request = new HttpPut(HttpRequestExecutor.RESOURCE_ROOT + "chore/" + this.id, this.str);
		HttpResponse response = HttpRequestExecutor.executeRequest(request);

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
		return this.users_assigned;
	}

/**/	public boolean removeUser(User toRemove)
	{
		return this.users_assigned.remove(toRemove);
		this.str = str.replace("<user id=\""+toRemove.getId()+"\" uri=\"/user/"+toRemove.getId()+"\">", "");
		HttpPut request = new HttpPut(HttpRequestExecutor.RESOURCE_ROOT + "chore/" + this.id, this.str);
		HttpResponse response = HttpRequestExecutor.executeRequest(request);
	}

	public void setId(String newId)
	{
	}
	public void setInstructions(String newInstructions)
	{
		this.str = str.replace("<intsructions>"+this.intsructions, "<intsructions>"+newInstructions);
		this.intsructions = newInstructions;
		HttpPut request = new HttpPut(HttpRequestExecutor.RESOURCE_ROOT + "chore/" + this.id, this.str);
		HttpResponse response = HttpRequestExecutor.executeRequest(request);
	}
	public void setName(String newName)
	{
		this.str = str.replace("<name>"+this.name, "<name>"+newName);
		this.name = newName;
		HttpPut request = new HttpPut(HttpRequestExecutor.RESOURCE_ROOT + "chore/" + this.id, this.str);
		HttpResponse response = HttpRequestExecutor.executeRequest(request);
	}
	public void setPointValue(double newPointValue)
	{
		this.str = str.replace("<pointValue>"+this.points, "<pointValue>"+Double.toString(newPointValue));
		this.points = newPointValue;
		HttpPut request = new HttpPut(HttpRequestExecutor.RESOURCE_ROOT + "chore/" + this.id, this.str);
		HttpResponse response = HttpRequestExecutor.executeRequest(request);
	}
}
