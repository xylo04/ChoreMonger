package com.choremonger.server;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.choremonger.shared.Family;

public interface FamilyResource {

	@POST
	@Consumes("application/xml")
	public Family createFamily(Family family);

	@DELETE
	@Path("{id}")
	public void deleteFamily(@PathParam("id") String id);

	@GET
	@Produces("application/xml")
	public Family getFamily();

	@GET
	@Path("{id}")
	@Produces("application/xml")
	public Family getFamily(@PathParam("id") String id);

	@PUT
	@Path("{id}")
	@Consumes("application/xml")
	public void updateFamily(@PathParam("id") String id, Family family);

}
