package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utilities.ElementUtility;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtility util;
	private Map<String, String> productMetaData = new HashMap<>(); //Insertion Order is not preserved
	//private Map<String, String> productMetaData = new LinkedHashMap<>(); //Insertion order is preserved
	// private Map<String, String> productMetaData = new TreeMap<>(); //Sorted based on null

	private By prod_header = By.cssSelector("div#content h1");
	private By address_images = By.cssSelector("ul.thumbnails img");
	private By product_metadata = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][1]//li");
	private By product_price = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][2]/li");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtility(this.driver);
	}

	public String getProductHeaderInfo() {
		String prodName = util.dogetElementText(prod_header);
		return prodName;
	}

	public int getNumberOfImages() {
		int number_actualImages = util.doGetElements(address_images).size();
		return number_actualImages;
	}

	private void getProductMetaData() {
		List<WebElement> doGetElements = util.doGetElements(product_metadata);
		System.out.println(doGetElements.size());

		for (WebElement elmnt : doGetElements) {
			String[] vls = elmnt.getText().split(":");
			String metaKey = vls[0].trim();
			String metaValue = vls[1].trim();
			productMetaData.put(metaKey, metaValue);

		}

	}

	private void getProductPrice() {
		List<WebElement> price = util.doGetElements(product_price);
		String prodPrice = price.get(0).getText();
		String extPrice = price.get(1).getText().split(":")[1].trim();

		productMetaData.put("price", prodPrice);
		productMetaData.put("extraprice", extPrice);
	}

	public Map<String, String> getProductDetails() {
		productMetaData.put("Product Name", "MacBook");
		getProductMetaData();
		getProductPrice();
		System.out.println(productMetaData);
		return productMetaData;
	}
}
