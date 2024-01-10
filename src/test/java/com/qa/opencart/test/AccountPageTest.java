package com.qa.opencart.test;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultsPage;

public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
		accntPage = lPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void editAccountTest() {
		boolean btnEdit = accntPage.checkEditButtonPresence();
		Assert.assertTrue(btnEdit);
	}

	@Test(priority = 2)
	public void accountFeaturesTest() {
		List<String> actFeatures = accntPage.getAllFeatures();
		Collections.sort(actFeatures);
		Collections.sort(AppConstant.ACCOUNT_OPTIONS);
		Assert.assertEquals(actFeatures, AppConstant.ACCOUNT_OPTIONS);
	}
	
	@Test(priority = 3)
	public void productSearchTest() {
		String productName = "MacBook";
		searchResultPage = accntPage.searchProduct(productName);
		productInfoPage = searchResultPage.selectProduct(productName);
		
		String actProdHeader = productInfoPage.getProductHeaderInfo();
		Assert.assertEquals(actProdHeader, productName);
	}
}
