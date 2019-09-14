package com.imrehorv.restserver.model;

import java.time.LocalDateTime;

public class BookingRecord {
	private LocalDateTime startDate;
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
