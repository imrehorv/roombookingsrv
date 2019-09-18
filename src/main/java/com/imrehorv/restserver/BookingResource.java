package com.imrehorv.restserver;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.imrehorv.restserver.model.BookingRecord;
import com.imrehorv.restserver.persistence.BookingRepo;

@Path("/booking")
public class BookingResource {
	Logger logger=Logger.getLogger(UserResource.class.getName());
	
	@Inject
	private BookingRepo bookingRepo;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response store(BookingRecord bookingRecord)
	{
		logger.info("store called bookingRecord:"+bookingRecord);
		bookingRepo.store(bookingRecord);
		return Response.accepted().entity(bookingRecord).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{date}")
	public Response load(@PathParam("date") String datestr)
	{
		logger.info("load called date:"+datestr);
		LocalDate date=LocalDate.parse(datestr);
		logger.info("parsed date:"+date);
		List<BookingRecord> result=bookingRepo.load(date);
		return Response.accepted().entity(result).build();
	}

}
