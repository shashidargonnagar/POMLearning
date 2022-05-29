package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.*;

@Epic ("Epic: US100 -Demo Open cart")
@Story(" US :101")
public class LoginPage {

	public WebDriver driver;
	public ElementUtil elementUtil;
	// 1. By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPassword = By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
	private By registerLink= By.linkText("Register");
	// 2. Page Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil= new ElementUtil(driver);
	}

	// 3. page methods
	@Step("Get Title")
	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	public String getLoginPageUrl() {
		return driver.getCurrentUrl();
	}

	public boolean isForgotPwdLinkExist() {
		return elementUtil.doIsDiplayed(forgotPassword);
	}
	
	public boolean isRegisterLinkExists() {
		return elementUtil.doIsDiplayed(registerLink);
	}
	@Step("Login with : {0} and password : {1}")
	public AccountsPage doLogin(String uname, String pwd) {
		elementUtil.doActionsSendKeys(emailId, uname);
		elementUtil.doActionsSendKeys(password, pwd);
		elementUtil.doClick(loginButton);
		
		return new AccountsPage(driver);
	}
	@Step("Naviagate to Registration link")
	public RegistrationPage clickRegistrationLink() {
		 elementUtil.doClick(registerLink);
		 return new RegistrationPage(driver);
	}
}
