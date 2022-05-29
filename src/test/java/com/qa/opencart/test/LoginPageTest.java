package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;



public class LoginPageTest extends BaseTest {

	@Description("Login Page Title Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		Assert.assertEquals(loginPage.getLoginPageTitle(),Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String url= loginPage.getLoginPageUrl();
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_URL_VALUE));
	}
	
	@Test(priority = 3)
	public void forgtorPwdLinkTest() {
		boolean urlExists= loginPage.isForgotPwdLinkExist();
		Assert.assertTrue(urlExists);
	}
	@Test(priority = 4)
	public void registerLinkTest() {
		boolean urlExists= loginPage.isRegisterLinkExists();
		Assert.assertTrue(urlExists);
	}
	
	@DataProvider
	public Object[][] getUserName(){
		Object[][] data= ExcelUtil.getTestData("Sheet1");
		return data;
	}
	@Test(priority = 5, dataProvider = "getUserName")
	public void loginTest(String uname, String pwd) {
		loginPage.doLogin(uname,pwd);
	}
}
