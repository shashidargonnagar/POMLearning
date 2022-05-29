package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.utils.Constants;

public class ProductInfoTest extends BaseTest {
	
	@BeforeClass
	public void productInfoPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Test
	public void productHeaderTest() throws InterruptedException {
		resultsPage=accountsPage.doSearch("macbook");
		prodInfoPage=resultsPage.selectProduct("MacBook Pro");
		Thread.sleep(5000);
		String prodHeader=prodInfoPage.getProductText();
		Assert.assertEquals(prodHeader,"MacBook Pro");
		Thread.sleep(5000);
	}
	
	@DataProvider
	public Object[][] getImageData(){
		return new Object[][] {
			{"macbook", "MacBook Pro" ,3},
			{"iMac", "iMac", 2},
			{"Apple", "Apple Cinema 30\"", 5}
		};
	}
	
	@Test(dataProvider = "getImageData" )
	public void productImageCount(String search,String prod, int imageCount) throws InterruptedException {
		resultsPage=accountsPage.doSearch(search);
		prodInfoPage=resultsPage.selectProduct(prod);
		Assert.assertEquals(prodInfoPage.getProductImageCount() , imageCount);
		Thread.sleep(5000);
	}
	
	@Test
	public void productmetDataTest() {
		resultsPage= accountsPage.doSearch("macbook");
		prodInfoPage= resultsPage.selectProduct("MacBook Pro");
		Map<String,String> result= prodInfoPage.getProductMetaData();
		result.forEach((k,v) -> System.out.println(k+" : "+v ));
		softAssert.assertEquals(result.get("ProductName"), "MacBook Pro1");
		softAssert.assertEquals(result.get("Brand"), "Apple1");
		softAssert.assertEquals(result.get("Product Code"), "Product 18");
		softAssert.assertEquals(result.get("price"), "$2,000.00");
	}
	
}
