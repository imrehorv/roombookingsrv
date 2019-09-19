package com.imrehorv.restserver.persistence.inmemory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import com.imrehorv.restserver.model.BookingRecord;

@Stateless
public class BookingRepoInMemory {
	
	Logger logger=Logger.getLogger(BookingRepoInMemory.class.getName());
	
	private Map<LocalDate,Map<LocalDateTime,Map<String,BookingRecord>>> map=new HashMap<>();
	
	
	public void store(BookingRecord record) {
		logger.info("store called record:"+record);
		LocalDate key=record.getStartDate().toLocalDate();
		Map<LocalDateTime,Map<String,BookingRecord>> innermap=map.get(key);
		if (innermap==null)
		{
			innermap=new HashMap<LocalDateTime,Map<String,BookingRecord>>();
			map.put(key, innermap);
		}
		Map<String,BookingRecord> roommap=innermap.get(record.getStartDate());
		if (roommap==null)
		{
			roommap=new HashMap<>();
			innermap.put(record.getStartDate(), roommap);
		}
		BookingRecord storedrecord=roommap.get(record.getRoomid());
		if (record.getUserid()==null)
		{
			roommap.put(record.getRoomid(),null);
		}
		else if (storedrecord==null)
		{
			roommap.put(record.getRoomid(), record);
		}
		debug();
	}
	
	public List<BookingRecord> load(LocalDate date) {
		Map<LocalDateTime,Map<String,BookingRecord>> innermap=map.get(date);
		List<BookingRecord> result=new ArrayList<>();
		if (innermap!=null)
		{
			for (Map<String,BookingRecord> recmap:innermap.values())
			{
				if (recmap!=null)
				{
					for (BookingRecord record:recmap.values())
					{
						if (record!=null)
						{
							result.add(record);
						}
					}
				}
			}
		}
		return result;
	}
	
	private void debug() {
		logger.info("map after save:"+map);
	}
}
