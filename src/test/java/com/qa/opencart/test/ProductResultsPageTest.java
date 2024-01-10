package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.SearchResultsPage;

public class ProductResultsPageTest extends BaseTest {

	@BeforeClass
	public void productInfoSetUp() {
		accntPage = lPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] { 
			{"MacBook","MacBook Pro", 4}, 
			{"MacBook","MacBook Air", 4},
			{"iMac", "iMac", 3}
		};
	}
	
	@Test(priority = 1, dataProvider = "getSearchData")
	public void prodImageTest(String searchKey, String productName, int numberOfImages) {
		
		searchResultPage = accntPage.searchProduct(searchKey);
		productInfoPage = searchResultPage.selectProduct(productName);
		
		int actImageCount = productInfoPage.getNumberOfImages();
		Assert.assertEquals(actImageCount, numberOfImages);
	}
	
	@Test(priority = 2)
	public void productDetailsTest() {
		searchResultPage = accntPage.searchProduct("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook");
		Map<String, String> productDetails = productInfoPage.getProductDetails();
		System.out.println();
		
		softAssert.assertEquals(productDetails.get("Brand"), "Apple");
		softAssert.assertEquals(productDetails.get("Product Code"), "Product 16");
		softAssert.assertEquals(productDetails.get("Reward Points"), "600");
		softAssert.assertEquals(productDetails.get("Availability"), "In Stock");
		softAssert.assertEquals(productDetails.get("price"), "$602.00");
		
		softAssert.assertEquals(productDetails.get("extraprice"), "$500.00");
		softAssert.assertEquals(productDetails.get("Product Name"), "MacBook");
		
		softAssert.assertAll();
		
	}
}
