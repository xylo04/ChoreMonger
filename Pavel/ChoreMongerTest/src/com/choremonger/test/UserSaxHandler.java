package com.choremonger.test;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.choremonger.shared.User;

public class UserSaxHandler extends DefaultHandler {

	private String characters;
	private User user;
	private boolean payingAttention = true;

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

		// Note to self: how can we do this without triggering User to update
		// with server?
		//
		// Decorator Pattern!?
		// Make a plain User implementor that just has data accessors, then
		// Decorate it with the newtork updater and hand it back; the client
		// (Driver class) never knows the difference.
		//
		if (qName.equalsIgnoreCase("chore")) {
			// break off another parser with a chore handler
			payingAttention = true;
		} else if (qName.equalsIgnoreCase("reward")) {
			// break off another parser with a reward handler
			payingAttention = true;
		} else if (qName.equalsIgnoreCase("user")) {
			// break off another parser with a user handler
			payingAttention = true;
		}

		if (!payingAttention) {
			return;
		}

		if (qName.equalsIgnoreCase("name")) {
			family.setName(characters);
		}

		// if we hit an end element that didn't have any characters, we don't
		// want this left over
		characters = "";
	}

	public Family getFamily() {
		return family;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		System.out.println("startElement " + qName);

		if (!payingAttention) {
			return;
		}

		if (qName.equalsIgnoreCase("family")) {
			family = new FamilyImpl();
			family.setId(attributes.getValue("id"));
		} else if (qName.equalsIgnoreCase("chore")) {
			// break off another parser with a chore handler
			payingAttention = false;
		} else if (qName.equalsIgnoreCase("reward")) {
			// break off another parser with a reward handler
			payingAttention = false;
		} else if (qName.equalsIgnoreCase("user")) {
			// break off another parser with a user handler
			payingAttention = false;
		}
	}

}
