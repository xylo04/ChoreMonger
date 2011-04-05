package com.choremonger.server;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.choremonger.shared.Chore;

public interface ChoreResource {

	@POST
	@Consumes("application/xml")
	public Chore createChore(Chore chore);

	@DELETE
	@Path("{id}")
	public void deleteChore(@PathParam("id") String id);

	@GET
	@Path("{id}")
	@Produces("application/xml")
	public Chore getChore(@PathParam("id") String id);

	@GET
	@Produces("application/xml")
	public ChoreList getChores();

	@PUT
	@Path("{id}")
	@Consumes("application/xml")
	public void updateChore(@PathParam("id") String id, Chore chore);

}