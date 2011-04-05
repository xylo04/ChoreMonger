package com.choremonger.server;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.choremonger.shared.Reward;

public interface RewardResource {

	@POST
	@Consumes("application/xml")
	public Reward createReward(Reward reward);

	@DELETE
	@Path("{id}")
	public void deleteReward(@PathParam("id") String id);

	@GET
	@Path("{id}")
	@Produces("application/xml")
	public Reward getReward(@PathParam("id") String id);

	@GET
	@Produces("application/xml")
	public RewardList getRewards();

	@PUT
	@Path("{id}")
	@Consumes("application/xml")
	public void updateReward(@PathParam("id") String id, Reward reward);

}