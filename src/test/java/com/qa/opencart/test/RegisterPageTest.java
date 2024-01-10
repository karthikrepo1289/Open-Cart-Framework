package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utilities.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registerPageSetup() {
		rgPage = lPage.navigateToRegisterPage();
	}

	@DataProvider
	public Object[][] getRegistrationData() {
		return ExcelUtil.readData(AppConstant.SHEET_NAME);
	}

	@Test(priority = 1, dataProvider = "getRegistrationData")
	public void registerUserCheck(String lastName, String firstName, String telephone, String pwd, String confirmPwd) {
		boolean isRegSucess = rgPage.userRegistration(lastName, firstName, getRandomEmailID(), telephone, pwd,
				confirmPwd);
		Assert.assertTrue(isRegSucess);
	}

	public String getRandomEmailID() {
		return "testautomation" + System.currentTimeMillis() + "@opencart.com";
		// return "testautomation"+UUID.randomUUID()+"@opencart.com";
	}
}
