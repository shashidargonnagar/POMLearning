package com.qa.opencart.pages;



import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	private By search=By.name("search");
	private By searchButton= By.cssSelector("div#search span");
	private By logout=By.linkText("Logout");
	private By accSectionHeader=By.cssSelector("div#content h2");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil= new ElementUtil(driver);
		
	}
	
	public String accountPageTitle() {
		return elementUtil.waitForTitleToBe( Constants.DEFAULT_TIME_OUT,Constants.ACC_PAGE_TITLE);
	}
	
	public boolean isLogOutLinkExist() {
		return elementUtil.doIsDiplayed(logout);
	}
	
	public boolean isSearchFieldExist() {
		return elementUtil.doIsDiplayed(search);
	}
	
	public List<String> getAccountSecList() {
		List<WebElement> secList=elementUtil.getElements(accSectionHeader);
		List<String> secHeaderList=new ArrayList<>();
		for(WebElement s:secList) {
			secHeaderList.add(s.getText());
		}
		return secHeaderList;
	}
	
	public ResultsPage doSearch(String searchKey) {
		elementUtil.doSendKeys(search, searchKey);
		elementUtil.doClick(searchButton);
		
		return new ResultsPage(driver);
	}

}
