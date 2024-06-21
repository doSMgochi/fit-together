package model.vo;

import java.sql.Date;

public class Statistics {
	int participantsId;
	String userId;
	int age;
	int ageCount;
	String gender;
	Date joinAt;
	public Statistics() {
		super();
	}
	public Statistics(int participantsId, String userId, int age, int ageCount, String gender, Date joinAt) {
		super();
		this.participantsId = participantsId;
		this.userId = userId;
		this.age = age;
		this.ageCount = ageCount;
		this.gender = gender;
		this.joinAt = joinAt;
	}
	public int getParticipantsId() {
		return participantsId;
	}
	public void setParticipantsId(int participantsId) {
		this.participantsId = participantsId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAgeCount() {
		return ageCount;
	}
	public void setAgeCount(int ageCount) {
		this.ageCount = ageCount;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getJoinAt() {
		return joinAt;
	}
	public void setJoinAt(Date joinAt) {
		this.joinAt = joinAt;
	}
	
}
