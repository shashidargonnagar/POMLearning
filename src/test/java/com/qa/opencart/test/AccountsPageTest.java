package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}
	
	@Test(priority = 1)
	public void accPageTitleTest() {
		String title=accountsPage.accountPageTitle();
		System.out.println("Account Page title is "+title);
		
		Assert.assertEquals(title, Constants.ACC_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void logOutLinkTest() {
		Assert.assertTrue(accountsPage.isLogOutLinkExist());
	}
	
	@Test(priority = 3)
	public void accPageSearchTest() {
		Assert.assertTrue(accountsPage.isSearchFieldExist());
	}
	
	@Test(priority = 4)
	public void accpageSecHeaderTest() {
		List<String> actualSecList=accountsPage.getAccountSecList();
		System.out.println("Actual section list "+actualSecList);
		Assert.assertEquals(actualSecList, Constants.EXP_ACCOUNTS_SECTIONS_LIST);
	}
	
	@DataProvider
	public Object[][] productData(){
		return new Object[][] {
			{"macbook"},
			{"iMac"},
			{"Apple"}
		};
	}
	
	@Test(priority = 5,dataProvider = "productData")
	public void searchTest(String productName) {
		resultsPage= accountsPage.doSearch(productName);
		Assert.assertTrue(resultsPage.getSearchCount()>0);
		
	}
	
	@DataProvider
	public Object[][] productSelectData(){
		return new Object[][] {
			{"iMac","iMac"},
			{"Apple","Apple Cinema 30\""},
			{"macbook","MacBook Pro"}
		};
	}
	
	@Test(priority = 6,dataProvider = "productSelectData" )
	public void selectProductTest(String searchKey,String selectProduct) throws InterruptedException {
		Thread.sleep(5000);
		resultsPage=accountsPage.doSearch(searchKey);
		prodInfoPage= resultsPage.selectProduct(selectProduct);
		Thread.sleep(5000);
	}

}
