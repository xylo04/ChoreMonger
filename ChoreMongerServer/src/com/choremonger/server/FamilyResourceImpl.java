package com.choremonger.server;

import javax.ws.rs.Path;

import com.choremonger.shared.Family;

@Path("/family")
public class FamilyResourceImpl implements FamilyResource {

	@Override
	public Family createFamily(Family family) {
		return family;
	}

	@Override
	public void deleteFamily(String id) {
		return;
	}

	@Override
	public Family getFamily() {
		return new FamilyImpl();
	}

	@Override
	public Family getFamily(String id) {
		FamilyImpl retval = new FamilyImpl();
		retval.setId(id);
		return retval;
	}

	@Override
	public void updateFamily(String id, Family family) {
		return;
	}

}
