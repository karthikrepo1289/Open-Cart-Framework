package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utilities.ElementUtility;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtility util;
	
	private By address_firstName = By.id("input-firstname");
	private By address_lastName = By.id("input-lastname");
	private By address_email = By.id("input-email");
	private By address_telephone = By.id("input-telephone");
	private By address_password = By.id("input-password");
	private By address_confirmPwd = By.id("input-confirm");
	private By address_continueBtn = By.xpath("//input[@value='Continue']");
	private By address_privacy = By.cssSelector("input[type='checkbox']");
	private By successMessg = By.cssSelector("div#content h1");
	private By address_registerLink = By.linkText(AppConstant.REGISTER_LINK);
	private By address_logOutLink = By.linkText("Logout");
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtility(this.driver);
	}
	
	public boolean userRegistration(String lastName, String firstName, String emailAddress, String telephone, String pwd, String confirmPwd) {
		util.doSendKeys(address_lastName, lastName);
		util.doSendKeys(address_firstName, firstName);
		util.doSendKeys(address_email, emailAddress);
		util.doSendKeys(address_telephone, telephone);
		util.doSendKeys(address_password, pwd);
		util.doSendKeys(address_confirmPwd, confirmPwd);
		util.clickElementWhenReady(address_privacy, AppConstant.MEDIUM_DEFAULT_WAIT);
		util.clickElementWhenReady(address_continueBtn, AppConstant.MEDIUM_DEFAULT_WAIT);
		
		String successMsg = util.checkElementVisibilitywithWait(successMessg, 5).getText();
		if(successMsg.contains(AppConstant.REGISTER_SUCCESS_MSG)) {
			util.doClick(address_logOutLink);
			util.doClick(address_registerLink);
			return true;
		}else {
			return false;
		}
	}
}
