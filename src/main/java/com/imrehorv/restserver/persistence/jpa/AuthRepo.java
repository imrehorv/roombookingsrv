package com.imrehorv.restserver.persistence.jpa;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.imrehorv.restserver.auth.AuthBean;

@Stateless
public class AuthRepo {
	
	@PersistenceContext
	EntityManager em;	
	
	@Inject
	AuthBean authBean;
	
	
	Logger logger=Logger.getLogger(AuthRepo.class.getName());
	
	
	public void storePassword(String userid,String password) throws NoSuchAlgorithmException {
		logger.info("storePassword called userid: "+userid);
		Auth entity=load(userid);
		String salt=authBean.getSalt();
		String hash=authBean.hash(salt, password);
		if (entity==null)
		{
			entity=new Auth();
			entity.setId(userid);
			entity.setSalt(salt);
			entity.setHash(hash);
			em.persist(entity);
		}
		else
		{
			entity.setId(userid);
			entity.setSalt(salt);
			entity.setHash(hash);
			em.merge(entity);
		}
	}
	
	public boolean verifyPassword(String userid,String password) throws NoSuchAlgorithmException
	{
		logger.info("verifyPassword called userid: "+userid);
		Auth entity=load(userid);
		if (entity==null)
		{
			logger.severe("verifyPassword user with userid:"+userid+" not found");
			return false;
		}
		String salt=entity.getSalt();
		String hash=authBean.hash(salt, password);
		return hash.equals(entity.getHash());
	}
	
	private Auth load(String userid) {
		Auth record=em.find(Auth.class, userid);
		return record;
	}
	
}
