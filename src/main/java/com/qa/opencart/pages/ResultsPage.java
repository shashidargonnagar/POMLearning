package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	private By searchHeader= By.cssSelector("div#content h1");
	private By searchResults= By.cssSelector("div.caption a");

	ResultsPage(WebDriver driver) {
		this.driver=driver;
		elementUtil= new ElementUtil(driver);
	}
	
	public String getSearchHeaderName() {
		return elementUtil.doGetText(searchHeader);
	}
	
	public List<String>  getResults() {
		List<String> results=elementUtil.getElementsTextList(searchResults, Constants.DEFAULT_TIME_OUT);
		
		return results;
	}
	
	public int getSearchCount() {
		return elementUtil.waitForElementsVisible(searchResults, Constants.DEFAULT_TIME_OUT).size();
	}
	
	public ProductInfoPage selectProduct(String mainProductName) {
		List<WebElement> wb= elementUtil.waitForElementsVisible(searchResults, Constants.DEFAULT_TIME_OUT);
		for(WebElement w:wb) {
			String result= w.getText().trim();
			if(result.equals(mainProductName)) {
				w.click();
				break;
			}
		}
		
		return new ProductInfoPage(driver);
		
	}
}
