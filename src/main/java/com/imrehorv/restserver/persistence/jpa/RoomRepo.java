package com.imrehorv.restserver.persistence.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.imrehorv.restserver.model.Room;

@Stateless
public class RoomRepo {
	
	@PersistenceContext
	EntityManager em;	
	
	Logger logger=Logger.getLogger(RoomRepo.class.getName());
	
	
	public void store(Room room) {
		logger.info("store called record:"+room);
		com.imrehorv.restserver.persistence.jpa.Room entity=find(room.getId());
		if (entity==null)
		{
			logger.info("insert record");
			em.persist(mapToDB(room));	
		}
		else 
		{
			logger.info("update record");			
			em.merge(mapToDB(room));
		}
	}
	
	public void delete(String id) {
		logger.info("delete called id:"+id);
		com.imrehorv.restserver.persistence.jpa.Room entity=find(id);
		if (entity!=null)
		{
			em.remove(entity);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Room> list() {
		return mapFromDB((List<com.imrehorv.restserver.persistence.jpa.Room>)em.createQuery(
		    "SELECT r FROM Room r ")
		    .getResultList());
	}
	
	public Room load(String roomid) {
		return mapFromDB(find(roomid));
	}

	private com.imrehorv.restserver.persistence.jpa.Room find(String roomid) {
		return em.find(com.imrehorv.restserver.persistence.jpa.Room.class,roomid);
	}

	private com.imrehorv.restserver.persistence.jpa.Room mapToDB(Room room) {
		if (room==null)
		{
			return null;
		}
		com.imrehorv.restserver.persistence.jpa.Room dbroom=new com.imrehorv.restserver.persistence.jpa.Room();
		dbroom.setId(room.getId());
		dbroom.setName(room.getName());
		return dbroom;
	}
	
	private List<Room> mapFromDB(List<com.imrehorv.restserver.persistence.jpa.Room> list) {
		List<Room> result=new ArrayList<>();
		for (com.imrehorv.restserver.persistence.jpa.Room room:list)
		{
			result.add(mapFromDB(room));
		}
		return result;
	}

	private Room mapFromDB(com.imrehorv.restserver.persistence.jpa.Room dbroom) {
		if (dbroom==null)
		{
			return null;
		}
		Room room=new Room();
		room.setId(dbroom.getId());
		room.setName(dbroom.getName());
		return room;
	}

	
}
