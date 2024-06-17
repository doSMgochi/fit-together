package model.vo;

import java.sql.Date;

public class Participants {
	int id;
	String userId;
	int eventId;
	Date joinAt;
	
	public Participants() {
		super();
	}
	
	public Participants(int id, String userId, int eventId, Date joinAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.eventId = eventId;
		this.joinAt = joinAt;
		
	}
	public int getId() {
		return id;
	}
	public String getUserId() {
		return userId;
	}
	public int getEventId() {
		return eventId;
	}
	public Date getJoinAt() {
		return joinAt;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public void setJoinAt(Date joinAt) {
		this.joinAt = joinAt;
	}
	
}
