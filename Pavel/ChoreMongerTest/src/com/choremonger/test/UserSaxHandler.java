package com.choremonger.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.choremonger.shared.User;

public class UserSaxHandler extends DefaultHandler {

	private String characters;
	private User user;

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// stash these until we know what they mean (in endElement)
		characters = new String(ch, start, length);
		System.out.println("characters " + characters);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		System.out.println("endElement " + qName);

		if (qName.equalsIgnoreCase("name")) {
			user.setName(characters);
		}
		else if (qName.equalsIgnoreCase("rewardPoints")) {
			user.setRewardPoints(Double.parseDouble(characters));
		}
		else if (qName.equalsIgnoreCase("email")) {
			user.setEmail(characters);
		}
		else if (qName.equalsIgnoreCase("dob")) {
			DateFormat formatter = new SimpleDateFormat();
			try{user.setDob(formatter.parse(characters));}
			catch (ParseException e) {
				System.out.println("error parsing!!!");
			}
		}

		// if we hit an end element that didn't have any characters, we don't
		// want this left over
		characters = "";
	}

	public User getUser() {
		return user;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		System.out.println("startElement " + qName);

		if (qName.equalsIgnoreCase("user")) {
			user = new UserImpl();
			user.setId(attributes.getValue("id"));	
		}
	}

}
