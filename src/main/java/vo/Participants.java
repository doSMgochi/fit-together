package vo;

import java.sql.Date;

public class Participants {
	int id;
	String userId;
	String eventId;
	Date joinAt;
	
	public Participants() {
		super();
	}
	
	public Participants(int id, String userId, String eventId, Date joinAt) {
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
	public String getEventId() {
		return eventId;
	}
	public Date getJoinAt() {
		return joinAt;
	}
	
}
