package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utilities.ElementUtility;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtility util;

	// By locators
	private By address_menus = By.xpath("//ul[@class='nav navbar-nav']/child::li/a");
	private By address_continuebtn = By.xpath("//h2[contains(text(),'New Customer')]/../a");
	private By address_features = By.xpath("//div[@class='list-group']/a");
	private By address_forgot_link = By.xpath("//a[contains(text(),'Forgotten')]");
	private By address_serachbox = By.name("search");
	private By address_contact = By.xpath("//i[@class='fa fa-phone']/parent::a/../span");
	private By address_username = By.id("input-email");
	private By address_pwd = By.id("input-password");
	private By address_loginButton = By.xpath("//input[@value='Login']");
	private By address_errMessage = By.xpath("//div[contains(text(),'Warning')]");
	private By address_registerLink = By.linkText(AppConstant.REGISTER_LINK);

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtility(driver);
	}

	@Step("It returns menus list")
	public List<String> getMenusList() {
		List<String> menuList = util.doGetElementsText(address_menus);
		return menuList;
	}

	public boolean isContinueButtonAvailable() {
		WebElement contButton = util.checkElementVisibilitywithWait(address_continuebtn,
				AppConstant.SHORT_DEFAULT_WAIT);
		return contButton.isDisplayed();
	}

	public String getContactNumber() {
		String contact = util.dogetElementText(address_contact);
		return contact;
	}

	// Page Chaining. Its the login method responsibility to return the next landing
	// page class object
	public AccountPage login(String userName, String passWord) {
		util.doActionSendKeys(address_username, userName);
		util.doActionSendKeys(address_pwd, passWord);
		util.clickElementWhenReady(address_loginButton, AppConstant.SHORT_DEFAULT_WAIT); // Landing Page : AccountsPage

		return new AccountPage(driver);
	}

	@Step("Login with Username : {0} and Password : {1}")
	public boolean loginWithInvalidCreds(String userName, String passWord) {

		util.doActionSendKeys(address_username, userName);
		util.doActionSendKeys(address_pwd, passWord);
		System.out.println("User Name and Password is " + userName + " " + passWord);
		util.clickElementWhenReady(address_loginButton, AppConstant.SHORT_DEFAULT_WAIT);

		boolean wrngMessage = util.checkElementPresence(address_errMessage);
		return wrngMessage;
	}

	public String getLoginTitle() {
		return driver.getTitle();
	}

	// Landing to new page
	public RegisterPage navigateToRegisterPage() {
		util.clickElementWhenReady(address_registerLink, 5);
		return new RegisterPage(driver);
	}
}

/*
 * Create a seperate java class per each webpage Page Class should contain all
 * the features of that particular webpage
 */
