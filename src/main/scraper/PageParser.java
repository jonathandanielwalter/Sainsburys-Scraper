package main.scraper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.json.JsonDto;
import main.json.JsonHelper;
import main.scraper.domains.Berry;

/**
 * 
 * @author Jonathan Walter This class takes a URL (hardcoded in the case of this
 *         example) and scrapes the page to get information to store in a JSON
 *         string.
 *
 */
public class PageParser {

	private double cumalativeCountOfCost;

	public String parsePage() {

		String sainsburysUrl = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

		try {
			Document berrysPage = Jsoup.connect(sainsburysUrl).get();
			Element productsContainer = berrysPage.getElementById("productsContainer");
			// get all the grid items from the products container to iterate through
			Elements items = productsContainer.getElementsByClass("gridItem");

			JsonDto jsonObject = new JsonDto();

			// for every berry on the page
			for (Element item : items) {

				// there should only be one element with this class so we get the first.
				Element productNameAndPromotions = item.getElementsByClass("productNameAndPromotions").first();

				String productPageLink = productNameAndPromotions.select("a").first().absUrl("href");

				// add the current berry to the list in the Json objet
				jsonObject.getBerrys().add(parseItemPage(productPageLink));
			}

			// after all the berry have been iterated over tell the dto the cumalative count
			jsonObject.setSumOfPrices(cumalativeCountOfCost);

			String json = JsonHelper.mashalObjectIntoJson(jsonObject);

			System.out.println("end of parser");
		} catch (IOException e) {

			System.out.println("The application could not connect to the example page");
			e.printStackTrace();

		}

		return "";
	}

	private Berry parseItemPage(String url) {

		try {
			Document singleBerryPage = Jsoup.connect(url).get();

			// get the title
			String title = getTitle(singleBerryPage);
			System.out.println("working on " + title);
			String kcalPer100G = getKcalPer100G(singleBerryPage);
			Double unitPrice = getUnitPrice(singleBerryPage);
			String description = getDescription(singleBerryPage);

			// add the current berrys cost to the count
			cumalativeCountOfCost = cumalativeCountOfCost + unitPrice;

			Berry berry = new Berry(title, kcalPer100G, unitPrice.toString(), description);
			return berry;

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("The application could not connect to a hyperlink");
			return null;
		}

	}

	private String getTitle(Document currentPage) {
		Element titleArea = currentPage.getElementsByClass("productSummary").first();
		String title = titleArea.getElementsByTag("h1").first().text();
		return title;
	}

	private String getKcalPer100G(Document currentPage) {
		String kcalPer100G;
		// first instance of tablerow0
		// first element under that
		Element nutritionTable = currentPage.getElementsByClass("nutritionTable").first();
		if (nutritionTable != null) {
			Elements tableRow = nutritionTable.getElementsByClass("tableRow0");

			// if there are no tableRow0 elements found its the other table structure. If
			// neither tables exist then there is no info
			if (tableRow.size() > 0) {
				kcalPer100G = nutritionTable.getElementsByClass("tableRow0").first().child(0).text();
			} else {
				kcalPer100G = nutritionTable.getElementsByClass("rowHeader").get(1).nextElementSibling().text();
			}
		} else {
			kcalPer100G = null;
		}
		return kcalPer100G;

	}

	private double getUnitPrice(Document currentPage) {
		Element productSummary = currentPage.getElementsByClass("productSummary").first();
		String ppUnit = productSummary.getElementsByClass("pricePerUnit").text();
		int indexOfSlash = ppUnit.indexOf("/");

		// remove the units of measure i.e. everything after the slash, then remove the
		// £ symbol
		ppUnit = ppUnit.substring(0, indexOfSlash);
		ppUnit = ppUnit.substring(1, ppUnit.length());

		Double ppUnitDouble = Double.valueOf(ppUnit);

		return ppUnitDouble;
	}

	private String getDescription(Document currentPage) {
		Element productText = currentPage.getElementsByClass("productText").first();
		String desc = productText.getElementsByTag("p").first().text();
		return desc;

	}
}
