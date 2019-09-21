package com.imrehorv.restserver.startup;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.imrehorv.restserver.UserResource;
import com.imrehorv.restserver.model.Room;
import com.imrehorv.restserver.model.User;
import com.imrehorv.restserver.persistence.jpa.RoomRepo;
import com.imrehorv.restserver.persistence.jpa.UserRepo;

@Singleton
@Startup
public class StartupBean {
	
	@Inject
	UserRepo userRepo;
	
	@Inject
	RoomRepo roomRepo;
	
	Logger logger=Logger.getLogger(UserResource.class.getName());	
	
	@PostConstruct
	private void startup() {
		logger.info("Application startup");		
		User admin=userRepo.load("admin");
		logger.info("Admin user:"+admin);		
		if (admin==null)
		{
			User user=new User();
			user.setId("admin");
			user.setName("Admin");
			user.setEmail("email@example.eu");
			userRepo.store(user);
		}
		User user1=userRepo.load("user1");
		logger.info("user1:"+user1);		
		if (user1==null)
		{
			User user=new User();
			user.setId("user1");
			user.setName("User1");
			user.setEmail("user1@example.eu");
			userRepo.store(user);
		}
		storeRoom("room1","Room1");
		storeRoom("room2","Room2");
		storeRoom("room3","Room3");
		storeRoom("room4","Room4");
		storeRoom("room5","Room5");
	}

	private void storeRoom(String id,String name) {
		Room room1=roomRepo.load(id);
		logger.info(id+" : "+room1);		
		if (room1==null)
		{
			Room room=new Room();
			room.setId(id);
			room.setName(name);
			roomRepo.store(room);
		}
	}	

}
