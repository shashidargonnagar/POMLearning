package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest{
	
	@BeforeClass
	public void registrationSetup() {
		registrationPage= loginPage.clickRegistrationLink();
	}
	
	@DataProvider
	public Object[][] getRegistrationData() {
		 Object data[][]=ExcelUtil.getTestData("Register");
		return data;
	}
	
	@Test(dataProvider = "getRegistrationData")
	public void registarionTest(String firstName, String lastName, String email, String telephone, String password, String subscribe) {
		boolean result;
		result=registrationPage.registration(firstName,lastName,email,telephone,password,subscribe);
		Assert.assertTrue(result);
		
	}

}
