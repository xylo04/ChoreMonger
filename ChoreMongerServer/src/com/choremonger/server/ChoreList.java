package com.choremonger.server;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "chores")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChoreList {

	@XmlElement(name = "chore")
	private List<ChoreImpl> chores = new ArrayList<ChoreImpl>();

	public void addAllChores(List<ChoreImpl> c) {
		chores.addAll(c);
	}

	public void addChore(ChoreImpl c) {
		chores.add(c);
	}
}
