package com.imrehorv.restserver.auth;

import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

public class AuthBeanTest {

	@Test
	public void testHash() throws NoSuchAlgorithmException
	{
		AuthBean bean=new AuthBean();
		String salt=bean.getSalt();
		System.out.println("salt:"+salt);
		String password="passwordabcd123";
		String hash1=bean.hash(salt, password);
		System.out.println("hash1:"+hash1);
		String hash2=bean.hash(salt, password);
		System.out.println("hash2:"+hash2);
		Assert.assertTrue(hash1.equals(hash2));
	}
	
}
