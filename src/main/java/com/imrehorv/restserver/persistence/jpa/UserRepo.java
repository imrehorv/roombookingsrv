package com.imrehorv.restserver.persistence.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.imrehorv.restserver.model.User;

@Stateless
public class UserRepo {
	
	@PersistenceContext
	EntityManager em;	
	
	Logger logger=Logger.getLogger(UserRepo.class.getName());
	
	
	public void store(User user) {
		logger.info("store called record:"+user);
		com.imrehorv.restserver.persistence.jpa.User entity=find(user.getId());
		if (entity==null)
		{
			logger.info("insert record");
			em.persist(mapToDB(user));	
		}
		else 
		{
			logger.info("update record");			
			em.merge(entity);
		}
	}
	
	public void delete(User user) {
		logger.info("delete called record:"+user);
		com.imrehorv.restserver.persistence.jpa.User entity=find(user.getId());
		if (entity!=null)
		{
			em.remove(entity);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<User> list() {
		return mapFromDB((List<com.imrehorv.restserver.persistence.jpa.User>)em.createQuery(
		    "SELECT u FROM User u ")
		    .getResultList());
	}
	
	public User load(String userid) {
		return mapFromDB(find(userid));
	}

	private com.imrehorv.restserver.persistence.jpa.User find(String userid) {
		return em.find(com.imrehorv.restserver.persistence.jpa.User.class,userid);
	}

	private com.imrehorv.restserver.persistence.jpa.User mapToDB(User user) {
		if (user==null)
		{
			return null;
		}
		com.imrehorv.restserver.persistence.jpa.User dbuser=new com.imrehorv.restserver.persistence.jpa.User();
		dbuser.setId(user.getId());
		dbuser.setName(user.getName());
		dbuser.setEmail(user.getEmail());
		return dbuser;
	}
	
	private List<User> mapFromDB(List<com.imrehorv.restserver.persistence.jpa.User> list) {
		List<User> result=new ArrayList<>();
		for (com.imrehorv.restserver.persistence.jpa.User user:list)
		{
			result.add(mapFromDB(user));
		}
		return result;
	}

	private User mapFromDB(com.imrehorv.restserver.persistence.jpa.User dbuser) {
		if (dbuser==null)
		{
			return null;
		}
		User user=new User();
		user.setId(dbuser.getId());
		user.setName(dbuser.getName());
		user.setEmail(dbuser.getEmail());
		return user;
	}

	
}
