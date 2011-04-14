package com.choremonger.server;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

public interface UserResource {

	@POST
	@Consumes("application/xml")
	public UserImpl createUser(UserImpl user);

	@DELETE
	@Path("{id}")
	public void deleteUser(@PathParam("id") String id);

	@GET
	@Path("{id}")
	@Produces("application/xml")
	public UserImpl getUser(@PathParam("id") String id);

	@GET
	@Produces("application/xml")
	public UserList getUsers();

	@PUT
	@Path("{id}")
	@Consumes("application/xml")
	public void updateUser(@PathParam("id") String id, UserImpl user);

}