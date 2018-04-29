package main.scraper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PageParser {

	public void parsePage() {
		
		String sainsburysUrl = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
		
		try {
			Document berrysPage = 	Jsoup.connect(sainsburysUrl).get();
			Element productsContainer = berrysPage.getElementById("productsContainer");
			//get all the grid items from the products container to iterate through
			Elements items = productsContainer.getElementsByClass("gridItem");
			
			for(Element item : items) {
				//there should only be one element with this class so we get the first.
				Element productNameAndPromotions = item.getElementsByClass("productNameAndPromotions").first();
				productNameAndPromotions.getElementsByTag("a").first();
			}
			
			System.out.println("end of parser");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void parseItemPage(String url){
		
	}

}
