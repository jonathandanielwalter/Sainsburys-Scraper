package main.scraper.domains;

public class Berry {

	private String title;
	private String kcal_per_100g;
	private String unit_price;
	private String description;
	
	public Berry(String title, String kcalPer100G, String unitPrice, String description) {
		this.title = title;
		this.kcal_per_100g = kcalPer100G;
		this.unit_price = unitPrice;
		this.description = description;
	}
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKcal_per_100g() {
		return kcal_per_100g;
	}

	public void setKcal_per_100g(String kcal_per_100g) {
		this.kcal_per_100g = kcal_per_100g;
	}

	public String getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



}
