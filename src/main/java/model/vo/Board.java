package model.vo;

import java.sql.Date;

public class Board {
	int id;
	String writerId;
	String title;
	String body;
	Date writedAt;
	int readCnt;
	String category;
	public Board() {
		super();
	}
	public Board(int id, String writerId, String title, String body, Date writedAt, int readCnt, String category) {
		super();
		this.id = id;
		this.writerId = writerId;
		this.title = title;
		this.body = body;
		this.writedAt = writedAt;
		this.readCnt = readCnt;
		this.category = category;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getWritedAt() {
		return writedAt;
	}
	public void setWritedAt(Date writedAt) {
		this.writedAt = writedAt;
	}
	public int getReadCnt() {
		return readCnt;
	}
	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
