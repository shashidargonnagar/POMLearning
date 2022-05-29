package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import dev.failsafe.internal.util.Assert;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		elementUtil= new ElementUtil(driver);
		
		
	}
	
	private By productHeader= By.cssSelector("div#content h1");
	private By productImage=By.cssSelector("li.image-additional");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn=By.id("button-cart");
	private By  prdMetaData=By.xpath("//div[@id='content']//ul[@class='list-unstyled'][1]/li");
	private By  priceData=By.xpath("//div[@id='content']//ul[@class='list-unstyled'][2]/li");

	
	
	public String getProductText() {
		return elementUtil.doGetText(productHeader);
	}
	
	public int getProductImageCount() {
		return elementUtil.waitForElementsVisible(productImage, Constants.DEFAULT_TIME_OUT).size();
	}
	
	public Map<String, String> getProductMetaData() {
		Map<String, String> prodMap=new HashMap<>();
		String prdname=elementUtil.doGetText(productHeader);
		prodMap.put("ProductName", prdname);
		getProdMetaData(prodMap);
		getPriceData(prodMap);
		
		return prodMap;
		
	}
	
	private void getProdMetaData(Map<String, String> prodMap) {
		List<WebElement> metaList= elementUtil.getElements(prdMetaData);
		for(WebElement m:metaList) {
			String metaText= m.getText();
			String metaKey= metaText.split(":")[0].trim();
			String metaValue= metaText.split(":")[1].trim();
			prodMap.put(metaKey, metaValue);
		}
	}
	
	private void getPriceData(Map<String, String> prodPriceMap) {
		List<WebElement> priceList= elementUtil.getElements(priceData);
		String actPrice= priceList.get(0).getText().trim();
		String exTaxPrice=priceList.get(1).getText().trim();
		prodPriceMap.put("price", actPrice);
		prodPriceMap.put(exTaxPrice.split(":")[0].trim(),exTaxPrice.split(":")[1].trim());
	}
}
