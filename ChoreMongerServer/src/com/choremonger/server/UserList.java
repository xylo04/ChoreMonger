package com.choremonger.server;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserList {

	@XmlElement(name = "user")
	private List<UserImpl> users = new ArrayList<UserImpl>();

	public void addAllUsers(List<UserImpl> u) {
		users.addAll(u);
	}

	public void addUser(UserImpl u) {
		users.add(u);
	}
}
