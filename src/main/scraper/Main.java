package main.scraper;

public class Main {

	public static void main(String[] args){
		System.out.println("Starting Main");
		PageParser parser = new PageParser();
		
		parser.parsePage();
		System.out.println("Parsed Page");
	}
	
}
