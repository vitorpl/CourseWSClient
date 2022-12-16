package com.vitor.coursewsclient.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="course")
public class Course {

	private long id;
	private String name;
	private String taughtBy;
	private double price;
	private int rating;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaughtBy() {
		return taughtBy;
	}

	public void setTaughtBy(String taughtBy) {
		this.taughtBy = taughtBy;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
