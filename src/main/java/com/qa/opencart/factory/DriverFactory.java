package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Shashidar Gonnagar
 *
 */
public class DriverFactory {
	public static String highlight;
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");
		String browser = prop.getProperty("browser");
		System.out.println("Browser name is : " + browser);
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			threadLocalDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFireFoxOptions());
			threadLocalDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			//driver = new EdgeDriver();
			threadLocalDriver.set(new EdgeDriver());
			break;

		default:
			System.out.println("Please pass the right browser.........");

		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}
	
	public synchronized WebDriver getDriver() {
		return threadLocalDriver.get();
	}

	/**
	 * 
	 * @return this method used for initialing property based on environment
	 */
	public Properties initProp() {

		prop = new Properties();
		FileInputStream ip = null;

		String env = System.getProperty("env");
		// Command to run tests >mvn clean install -Denv=uat
		if (env == null) {
			try {
				ip = new FileInputStream("./src/test/resource/config/config.properties");

			} catch (FileNotFoundException e) {
				// TODO: handle exception
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {

			try {
				System.out.println("Running on Environment " + env);
				switch (env.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resource/config/qa.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resource/config/uat.config.properties");
					break;

				default:
					break;
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;

	}
	
	/**
	 * Take Screenshot
	 */
	
	public String getScreenshot() {
		File srcFile=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir"+"/screehshot/"+System.currentTimeMillis()+".png");
		File destination= new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
