package com.imrehorv.restserver.auth;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.ejb.Stateless;

@Stateless
public class AuthBean {
	
	public String getSalt()
	{
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[16];
		random.nextBytes(bytes);
		return new String(bytes);
	}
	
	public String hash(String salt,String password) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] bytes=salt.getBytes(StandardCharsets.UTF_8);
		md.update(bytes);
		byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
		return new String(hashedPassword);
	}
	
}
