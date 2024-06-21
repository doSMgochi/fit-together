package model.vo.complex;

public class EventTagCount {
	String tag;
	int count;
	public EventTagCount() {
		super();
	}
	public EventTagCount(String tag, int count) {
		super();
		this.tag = tag;
		this.count = count;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
