package vo;


public class Gym {
	int id;
	String name;
	String type;
	String region;
	String agency;
	String manager;
	
	public Gym() {
		super();
	}
	
	public Gym(int id, String name, String type, String region, String agency, String manager) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.region = region;
		this.agency = agency;
		this.manager = manager;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public String getRegion() {
		return region;
	}
	public String getAgency() {
		return agency;
	}
	public String getManager() {
		return manager;
	}
	
}
