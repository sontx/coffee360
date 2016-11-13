package com.dutproject.coffee360admin.model.bean;

public class ShopReport {
	private int id;
	private String placeName;
	private String description;
	private int quantity;
	
	public ShopReport() {}
	
	public ShopReport(int reportId, String placeName, String description, int quantity) {
		setId(reportId);
		setPlaceName(placeName);
		setDescription(description);
		setQuantity(quantity);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
