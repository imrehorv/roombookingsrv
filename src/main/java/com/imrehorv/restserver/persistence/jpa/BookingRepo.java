package com.imrehorv.restserver.persistence.jpa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.imrehorv.restserver.model.BookingRecord;

@Stateless
public class BookingRepo {
	
	@PersistenceContext
	EntityManager em;	
	
	Logger logger=Logger.getLogger(BookingRepo.class.getName());
	
	
	public void store(BookingRecord record) {
		logger.info("store called record:"+record);
		Booking entity=load(record);
		if (entity==null)
		{
			logger.info("save record");
			em.persist(mapRecord(record,UUID.randomUUID().getLeastSignificantBits()));	
		}
		else 
		{
			logger.info("delete record");			
			em.remove(entity);
		}
	}
	
	private Booking mapRecord(BookingRecord record,long id) {
		Booking result=new Booking();
		result.setStartDate(record.getStartDate());
		result.setEndDate(record.getEndDate());
		result.setUserid(record.getUserid());
		result.setRoomid(record.getRoomid());
		result.setId(id);
		result.setDate(record.getStartDate().toLocalDate());
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<BookingRecord> load(LocalDate date) {
		try {
			return mapToRecord((List<Booking>)em.createQuery(
			    "SELECT b FROM Booking b WHERE "
					    + "b.date = :date")
				    .setParameter("date", date)
			    .getResultList());
		} catch (NoResultException ne) {
			logger.info("no result");
			return new ArrayList<>();
		}
	}
	
	private List<BookingRecord> mapToRecord(List<Booking> list) {
		List<BookingRecord> result=new ArrayList<>();
		for (Booking booking:list)
		{
			result.add(mapToRecord(booking));
		}
		return result;
	}

	private BookingRecord mapToRecord(Booking booking) {
		BookingRecord record=new BookingRecord();
		record.setStartDate(booking.getStartDate());
		record.setEndDate(booking.getEndDate());
		record.setUserid(booking.getUserid());
		record.setRoomid(booking.getRoomid());
		return record;
	}

	private Booking load(BookingRecord record) {
		try {
			return (Booking)em.createQuery(
			    "SELECT b FROM Booking b WHERE "
					    + "b.startDate = :startDate AND "
				    + "b.roomid = :roomid")
				    .setParameter("startDate", record.getStartDate())
				    .setParameter("roomid", record.getRoomid())
			    .setMaxResults(10)
			    .getSingleResult();
		} catch (NoResultException ne) {
			logger.info("no result");
			return null;
		}
	}
	
}
