package com.food.model;

public class Restaurant {

	private int id;
	private String name;
	private String cuisine;
	private double rating;
	private String imagePath;
	
	public Restaurant() {
		
	}

	public Restaurant(int id, String name, String cuisine, double rating, String imagePath) {
		super();
		this.id = id;
		this.name = name;
		this.cuisine = cuisine;
		this.rating = rating;
		this.imagePath = imagePath;
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

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", cuisine=" + cuisine + ", rating=" + rating
				+ ", imagePath=" + imagePath + "]";
	}
	
	
}
