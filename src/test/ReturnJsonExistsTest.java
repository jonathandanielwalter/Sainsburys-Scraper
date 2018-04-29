package test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import main.scraper.PageParser;

class ReturnJsonExistsTest {

	@Test
	void test() {
		
		PageParser parser = new PageParser();
		String jsonResult = parser.parsePage();
		
		assertNotNull(jsonResult);
		assertNotEquals("" , jsonResult);
	}

}
