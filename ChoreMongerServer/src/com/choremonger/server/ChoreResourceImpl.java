package com.choremonger.server;

import java.util.logging.Logger;

import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;

import com.choremonger.shared.Chore;

@Path("/chore")
public class ChoreResourceImpl implements ChoreResource {

	private Logger log = Logger.getLogger(ChoreResourceImpl.class.getName());

	@Override
	public ChoreImpl createChore(ChoreImpl toCreate) {
		log.info("Create chore " + toCreate.getName());
		toCreate.setId(null);
		FamilyResource fr = new FamilyResourceImpl();
		FamilyImpl f = fr.getFamily();
		f.addChore(toCreate);
		fr.updateFamily(f.getId(), f);
		log.info("Chore got id " + toCreate.getId());
		return toCreate;
	}

	@Override
	public void deleteChore(String id) {
		log.info("Delete chore id " + id);
		boolean found = false;
		FamilyResource fr = new FamilyResourceImpl();
		FamilyImpl f = fr.getFamily();
		for (Chore c : f.getChores()) {
			if (c.getId().equals(id)) {
				f.removeChore(c);
				found = true;
			}
		}
		if (found) {
			fr.updateFamily(f.getId(), f);
			log.info("OK");
		} else {
			log.warning("Not found");
		}
	}

	@Override
	public ChoreImpl getChore(String id) {
		log.info("Retrieve chore id " + id);
		FamilyResource fr = new FamilyResourceImpl();
		FamilyImpl f = fr.getFamily();
		ChoreImpl retval = null;
		for (Chore c : f.getChores()) {
			if (c.getId().equals(id)) {
				retval = (ChoreImpl) c;
			}
		}
		if (retval == null) {
			log.warning("Not found");
			throw new WebApplicationException(404);
		}
		log.info("OK");
		return retval;
	}

	@Override
	public ChoreList getChores() {
		log.info("Retrieve chore list");
		FamilyResource fr = new FamilyResourceImpl();
		FamilyImpl f = fr.getFamily();
		ChoreList retval = new ChoreList();
		for (Chore c : f.getChores()) {
			retval.addChore((ChoreImpl) c);
		}
		log.info("OK");
		return retval;
	}

	@Override
	public void updateChore(String id, ChoreImpl newValue) {
		log.info("Update chore id " + id);
		if (!newValue.getId().equals(id)) {
			// uri and xml document didn't refer to the same ID
			throw new WebApplicationException(400);
		}
		FamilyResource fr = new FamilyResourceImpl();
		FamilyImpl f = fr.getFamily();
		ChoreImpl toUpdate = null;
		for (Chore c : f.getChores()) {
			if (c.getId().equals(id)) {
				toUpdate = (ChoreImpl) c;
			}
		}
		if (toUpdate == null) {
			log.warning("Not found");
			throw new WebApplicationException(404);
		}
		f.removeChore(toUpdate);
		f.addChore(newValue);
		fr.updateFamily(f.getId(), f);
		log.info("OK");
	}

}
