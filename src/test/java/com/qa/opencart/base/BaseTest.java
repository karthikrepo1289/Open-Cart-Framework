package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.driverfactory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

import io.qameta.allure.Step;

public class BaseTest {

	protected WebDriver driver;
	DriverFactory dFactory;
	protected LoginPage lPage;
	protected AccountPage accntPage;
	protected Properties prop;
	protected SearchResultsPage searchResultPage;
	protected ProductInfoPage productInfoPage;
	protected SoftAssert softAssert;
	protected RegisterPage rgPage;

	@Parameters({ "browser" })
	@BeforeTest
	@Step("Launching Browser")
	public void setUp(String browserName) {
		dFactory = new DriverFactory();
		prop = dFactory.initProp();

		if (browserName != null) {
			prop.setProperty("browser", browserName);
		}
		driver = dFactory.init(prop);
		lPage = new LoginPage(driver);
		softAssert = new SoftAssert();

	}

	@AfterTest
	@Step("Closing the Browser")
	public void tearDown() {
		driver.close();
	}
}
