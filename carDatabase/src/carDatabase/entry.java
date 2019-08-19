package carDatabase;

import java.math.BigDecimal;

public class entry {

	private int id;
	private String name;
	private String street;
	private String date;
	private String cost;
	private String comment;
	
	public entry(int id, String name, String street, String date,
			String cost, String comment) {
		super();
		this.id = id;
		this.name = name;
		this.street = street;
		this.date = date;
		this.cost = cost;
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return String
				.format("entry [id=%s, name=%s, street=%s, date=%s, cost=%s, comment=%s]",
						id, name, street, date, cost, comment);
	}
	
	
		
}

