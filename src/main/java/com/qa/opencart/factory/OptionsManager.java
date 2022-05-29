package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions chromeOptions;
	private FirefoxOptions fireFoxOptions;
	public OptionsManager(Properties prop) {
		this.prop= prop;
	}
	
	public ChromeOptions getChromeOptions() {
		chromeOptions= new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless")))	chromeOptions.addArguments("--headless"); 
		if(Boolean.parseBoolean(prop.getProperty("incognito")))	chromeOptions.addArguments("--incognito");
		return chromeOptions;
	}
	
	public FirefoxOptions getFireFoxOptions() {
		fireFoxOptions= new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))	fireFoxOptions.addArguments("--headless"); 
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim()))	fireFoxOptions.addArguments("--incognito");
		return fireFoxOptions;
	}
	

}
