package com.choremonger.server;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

public interface FamilyResource {

	@POST
	@Consumes("application/xml")
	public FamilyImpl createFamily(FamilyImpl family);

	@DELETE
	@Path("{id}")
	public void deleteFamily(@PathParam("id") String id);

	@GET
	@Produces("application/xml")
	public FamilyImpl getFamily();

	@GET
	@Path("{id}")
	@Produces("application/xml")
	public FamilyImpl getFamily(@PathParam("id") String id);

	@PUT
	@Path("{id}")
	@Consumes("application/xml")
	public void updateFamily(@PathParam("id") String id, FamilyImpl family);

}
