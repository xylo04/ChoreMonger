package com.choremonger.test;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.choremonger.shared.Reward;

public class RewardSaxHandler extends DefaultHandler {

	private String characters;
	private Reward currentReward;
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

		// Note to self: how can we do this without triggering Family to update
		// with server?
		//
		// Decorator Pattern!?
		// Make a plain Family implementor that just has data accessors, then
		// Decorate it with the newtork updater and hand it back; the client
		// (Driver class) never knows the difference.
		//
		/*if (qName.equalsIgnoreCase("description")) {
			payingAttention = true;
		} else if (qName.equalsIgnoreCase("isOneTime")) {
			payingAttention = true;
		} else if (qName.equalsIgnoreCase("pointValue")) {
			payingAttention = true;
		}
		else if (qName.equalsIgnoreCase("name")) {
			payingAttention = true;
		}
		if (!payingAttention) {
			return;
		}
*/		
		if (qName.equalsIgnoreCase("name")) {
			currentReward.setName(characters);
		}

		// if we hit an end element that didn't have any characters, we don't
		// want this left over
		characters = "";
		
	}

	public Reward getReward() {
		return currentReward;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		System.out.println("startElement " + qName);
		if (!payingAttention) {
			return;
		}

		if (qName.equalsIgnoreCase("family")) {
			payingAttention=false;
		} else if (qName.equalsIgnoreCase("chore")) {
			// break off another parser with a chore handler
			payingAttention = false;
		} else if (qName.equalsIgnoreCase("reward")) {
			payingAttention=true;
			currentReward=new RewardImpl();
			currentReward.setId(attributes.getValue("id"));
			//reward=new RewardImpl(attributes.getValue("id"), attributes.getValue("description"),attributes.getValue("name"), Double.parseDouble(attributes.getValue("pointValue")), Boolean.parseBoolean(attributes.getValue("isOneTime")));
		} 
		else if (qName.equalsIgnoreCase("user")) {
			// break off another parser with a user handler
			payingAttention = false;
		}
	}

}
