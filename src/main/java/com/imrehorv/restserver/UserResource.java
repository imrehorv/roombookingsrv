package com.imrehorv.restserver;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.imrehorv.restserver.model.User;

@Path("/user")
public class UserResource {

	Logger logger=Logger.getLogger(UserResource.class.getName());
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response store(User user)
	{
		logger.info("store called user:"+user);
		return Response.accepted().entity(user).build();
	}
}
