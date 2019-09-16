package com.imrehorv.restserver.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class BookingRecord {
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime startDate;
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime endDate;
	
    private String userid;
    private String username;
    private String roomid;
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRoomid() {
		return roomid;
	}
	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}
	@Override
	public String toString() {
		return "BookingRecord [startDate=" + startDate + ", endDate=" + endDate + ", userid=" + userid + ", username="
				+ username + ", roomid=" + roomid + "]";
	}
    
}
