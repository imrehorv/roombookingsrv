package com.imrehorv.restserver.persistence;

import java.util.logging.Logger;

import javax.ejb.Stateless;

import com.imrehorv.restserver.model.BookingRecord;

@Stateless
public class BookingRepo {
	
	Logger logger=Logger.getLogger(BookingRepo.class.getName());
	
	public void store(BookingRecord record) {
		logger.info("store called record:"+record);
		
	}
}
