package main.json;

import java.util.ArrayList;
import java.util.List;

import main.scraper.domains.Berry;

public class JsonDto {

	private List<Berry> berrys = new ArrayList<>();
	private double sumOfPrices;

	public List<Berry> getBerrys() {
		return berrys;
	}

	public void setBerrys(List<Berry> berrys) {
		this.berrys = berrys;
	}

	public double getSumOfPrices() {
		return sumOfPrices;
	}

	public void setSumOfPrices(double sumOfPrices) {
		this.sumOfPrices = sumOfPrices;
	}

}
