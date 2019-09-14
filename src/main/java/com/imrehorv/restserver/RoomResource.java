package com.imrehorv.restserver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.imrehorv.restserver.model.Room;

@Path("/rooms")
public class RoomResource {

	Logger logger=Logger.getLogger(RoomResource.class.getName());
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRooms()
	{
		logger.info("getRooms called");
		
		List<Room> roomlist=new ArrayList<Room>();
		
		Room room=new Room();
		roomlist.add(room);
		room.setId("room1");
		room.setName("Room1");
		
		room=new Room();
		roomlist.add(room);
		room.setId("room2");
		room.setName("Room2");
		
		room=new Room();
		roomlist.add(room);
		room.setId("room3");
		room.setName("Room3");
		
		room=new Room();
		roomlist.add(room);
		room.setId("room4");
		room.setName("Room4");
		
		return Response.ok().entity(roomlist).build();
	}
	
}
