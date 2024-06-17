package model.vo;

import java.sql.Date;

public class Event {
	int id;
	String title;
	String description;
	String tag;
	int gymId;
	String hostId;
	Date openAt;
	int capacity;
	int cur;
	Date registerAt;
	
	public Event() {
		super();
	}
	
	public Event(int id, String title, String description, String tag, int gymId, String hostId, Date openAt,
			int capacity, int cur, Date registerAt) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.tag = tag;
		this.gymId = gymId;
		this.hostId = hostId;
		this.openAt = openAt;
		this.capacity = capacity;
		this.cur = cur;
		this.registerAt = registerAt;
	}
	
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public String getTag() {
		return tag;
	}
	public int getGymId() {
		return gymId;
	}
	public String getHostId() {
		return hostId;
	}
	public Date getOpenAt() {
		return openAt;
	}
	public int getCapacity() {
		return capacity;
	}
	public int getCur() {
		return cur;
	}
	public Date getRegisterAt() {
		return registerAt;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setGymId(int gymId) {
		this.gymId = gymId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public void setOpenAt(Date openAt) {
		this.openAt = openAt;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setCur(int cur) {
		this.cur = cur;
	}

	public void setRegisterAt(Date registerAt) {
		this.registerAt = registerAt;
	}
	
}
