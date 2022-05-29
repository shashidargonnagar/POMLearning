package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telePhone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confPassword = By.id("input-confirm");
	private By subscriberYes = By.xpath("//label[@class='radio-inline']//input[@value='1']");
	private By subscriberNo = By.xpath("//label[@class='radio-inline']//input[@value='0']");
	private By agreeCheck = By.name("agree");
	private By successMsg = By.cssSelector("div#content h1");
	private By logOutlink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	private By continueButton = By.xpath("//input[@value='Continue' and @class='btn btn-primary']");

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public boolean registration(String firstName, String LastName, String email, String telephone, String password,
			String subscribe) {

		fillRegForm(firstName, LastName, email, telephone, password, subscribe);
		elementUtil.doClick(continueButton);
		return getRegistrationStatus();
	}

	private void fillRegForm(String firstName, String LastName, String email, String telephone, String password,
			String subscribe) {
		elementUtil.doSendKeys(this.firstName, firstName);
		elementUtil.doSendKeys(this.lastName, LastName);
		elementUtil.doSendKeys(this.email, email);
		elementUtil.doSendKeys(this.telePhone, telephone);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doSendKeys(this.confPassword, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			elementUtil.doClick(subscriberYes);
		} else {
			elementUtil.doClick(subscriberNo);
		}
		elementUtil.doClick(agreeCheck);
	}

	private boolean getRegistrationStatus() {
		String msg = elementUtil.doGetText(this.successMsg);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (msg.contains(Constants.REGISTER_SUCCESS_MESSAGE)) {
			elementUtil.doClick(logOutlink);
			elementUtil.doClick(registerLink);
			return true;
		}
		return false;
	}

}
