package model.vo;

public class Comment {
	int id;
	String userId;
	int eventId;
	String body;

	public Comment() {
		super();
	}

	public Comment(int id, String userId, int eventId, String body) {
		super();
		this.id = id;
		this.userId = userId;
		this.eventId = eventId;
		this.body = body;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
