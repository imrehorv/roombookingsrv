package com.imrehorv.restserver;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.imrehorv.restserver.model.User;
import com.imrehorv.restserver.persistence.jpa.UserRepo;

@Path("/user")
public class UserResource {

	Logger logger=Logger.getLogger(UserResource.class.getName());
	
	@Inject
	UserRepo userRepo;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response store(User user)
	{
		logger.info("store called user:"+user);
		userRepo.store(user);
		return Response.accepted().entity(user).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list()
	{
		logger.info("user list called");
		return Response.accepted().entity(userRepo.list()).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response load(@PathParam("id") String id)
	{
		logger.info("user list called");
		return Response.accepted().entity(userRepo.load(id)).build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response delete(@PathParam("id") String id)
	{
		logger.info("delete called id:"+id);
		userRepo.delete(id);
		return Response.accepted().build();
	}
	
}
