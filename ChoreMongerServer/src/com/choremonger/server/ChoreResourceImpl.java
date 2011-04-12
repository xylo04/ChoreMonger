package com.choremonger.server;

import javax.ws.rs.Path;

import com.choremonger.shared.Chore;

@Path("/chore")
public class ChoreResourceImpl implements ChoreResource {

	@Override
	public Chore createChore(Chore chore) {
		chore.setId("1");
		return chore;
	}

	@Override
	public void deleteChore(String id) {
		return;
	}

	@Override
	public Chore getChore(String id) {
		ChoreImpl retval = new ChoreImpl();
		retval.setId(id);
		return retval;
	}

	@Override
	public ChoreList getChores() {
		ChoreList retval = new ChoreList();
		retval.addChore(new ChoreImpl());
		return retval;
	}

	@Override
	public void updateChore(String id, Chore chore) {
		return;
	}

}
