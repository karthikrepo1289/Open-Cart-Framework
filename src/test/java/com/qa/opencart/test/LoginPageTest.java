package com.qa.opencart.test;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstant;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("")
@Story("")
public class LoginPageTest extends BaseTest {

	@Description("This is my login menu's test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void menuListTest() {
		List<String> menusList = lPage.getMenusList();
		Collections.sort(menusList);
		Collections.sort(AppConstant.LOGIN_MENU_ITEMS);
		Assert.assertEquals(menusList, AppConstant.LOGIN_MENU_ITEMS);
	}

	@Test(priority = 2)
	public void continueButtonAvailTest() {
		boolean cont_visibility = lPage.isContinueButtonAvailable();
		Assert.assertTrue(cont_visibility, "New Registration is not available");
	}

	@Test(priority = 3)
	public void contactTest() {
		String actContact = lPage.getContactNumber();
		String expContact = AppConstant.CONTACT_DETAILS;
		Assert.assertEquals(actContact, expContact, "Contact is not matched");
	}

	@Test(priority = 4)
	public void invalidCredLoginTest() {
		boolean isWarningMsgDisplayed = lPage.loginWithInvalidCreds("KTS", "KTS");
		Assert.assertTrue(isWarningMsgDisplayed);
	}

	@Test(priority = 5)
	public void loginTitleTest() {
		String actualTitle = lPage.getLoginTitle();
		String expTitle = AppConstant.LOGIN_PAGE_TITLE;
		Assert.assertEquals(actualTitle, expTitle, "Title is not matched");
	}
}
