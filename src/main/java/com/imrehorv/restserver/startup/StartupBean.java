package com.imrehorv.restserver.startup;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.imrehorv.restserver.UserResource;
import com.imrehorv.restserver.model.User;
import com.imrehorv.restserver.persistence.jpa.UserRepo;

@Singleton
@Startup
public class StartupBean {
	
	@Inject
	UserRepo userRepo;
	
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
	}	

}
