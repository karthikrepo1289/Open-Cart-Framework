package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utilities.ElementUtility;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtility util;
	
	//Dynamic By locators should not be included in declaration
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtility(this.driver);
	}

	public ProductInfoPage selectProduct(String prodName) {
		By productName = By.linkText(prodName);
		util.clickElementWhenReady(productName, AppConstant.MEDIUM_DEFAULT_WAIT);
		return new ProductInfoPage(driver);
	}
}
