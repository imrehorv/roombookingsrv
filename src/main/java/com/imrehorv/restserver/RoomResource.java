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

import com.imrehorv.restserver.model.Room;
import com.imrehorv.restserver.persistence.jpa.RoomRepo;

@Path("/room")
public class RoomResource {

	Logger logger=Logger.getLogger(RoomResource.class.getName());
	
	@Inject
	RoomRepo roomRepo;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response store(Room room)
	{
		logger.info("store called room:"+room);
		roomRepo.store(room);
		return Response.accepted().entity(room).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list()
	{
		logger.info("room list called");
		return Response.accepted().entity(roomRepo.list()).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response load(@PathParam("id") String id)
	{
		logger.info("room list called");
		return Response.accepted().entity(roomRepo.load(id)).build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response delete(@PathParam("id") String id)
	{
		logger.info("delete called id:"+id);
		roomRepo.delete(id);
		return Response.accepted().build();
	}
	
	
}
