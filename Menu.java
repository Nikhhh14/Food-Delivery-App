package com.food.model;

public class Menu {
	
	private int id;
	private int restaurantId;
	private String name;
	private String description;
	private double price;
	private String imagePath;
	
	public Menu() {
		// TODO Auto-generated constructor stub
	}

	public Menu(int id, int restaurantId, String name, String description, double price, String imagePath) {
		super();
		this.id = id;
		this.restaurantId = restaurantId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imagePath = imagePath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", restaurantId=" + restaurantId + ", name=" + name + ", description=" + description
				+ ", price=" + price + ", imagePath=" + imagePath + "]";
	}
	
	
	

}
