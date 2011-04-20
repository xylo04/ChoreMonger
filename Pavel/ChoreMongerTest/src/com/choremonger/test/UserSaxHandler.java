package com.choremonger.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.choremonger.shared.User;

public class UserSaxHandler extends DefaultHandler {

	private String characters;
	private String Name;
	private Double RewardPoints;
	private String Email;
	private Date DoB;
	private User user;
	private String Id;

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
			Name = characters;
		}
		else if (qName.equalsIgnoreCase("rewardPoints")) {
			RewardPoints = Double.parseDouble(characters);
		}
		else if (qName.equalsIgnoreCase("email")) {
			Email = characters;
		}
		else if (qName.equalsIgnoreCase("dob")) {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try{DoB = formatter.parse(characters);}
			catch (ParseException e) {
				System.out.println("error parsing!!!");
			}
		}
		else if (qName.equalsIgnoreCase("user")) {
			user = new UserImpl(Name,RewardPoints,Email,DoB);
			user.setId(Id);
		}
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
			Id = attributes.getValue("id");	
		}
	}

}
