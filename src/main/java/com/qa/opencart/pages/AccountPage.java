package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utilities.ElementUtility;

public class AccountPage {

	private WebDriver driver;
	private ElementUtility util;

	private By addressEditButton = By.xpath("//a[contains(text(),'Edit Account')]");
	private By lstOfAvailOptions = By.xpath("//aside[@id='column-right']//a");
	private By search_input = By.name("search");
	private By search_icon = By.xpath("//input[@name='search']/following::i[contains(@class,'search')]");
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtility(this.driver);
	}

	public boolean checkEditButtonPresence() {
		boolean editButtonPresence = util.checkElementPresence(addressEditButton);
		return editButtonPresence;
	}

	public List<String> getAllFeatures() {
		List<String> features = util.doGetElementsText(lstOfAvailOptions);
		return features;
	}
	
	//Page Chaining : Method that should responsible to instantiate the respective landing page
	public SearchResultsPage searchProduct(String productName) {
		util.getElement(search_input).clear();
		util.doSendKeysWithWait(search_input, AppConstant.MEDIUM_DEFAULT_WAIT, productName);
		util.clickElementWhenReady(search_icon, AppConstant.MEDIUM_DEFAULT_WAIT);
		return new SearchResultsPage(driver);
	}
}
