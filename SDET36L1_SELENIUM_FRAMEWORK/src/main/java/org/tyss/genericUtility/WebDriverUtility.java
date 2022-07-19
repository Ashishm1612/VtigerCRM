package org.tyss.genericUtility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class contains reusable methods for webdriver
 * @author Ashish
 *
 */
public class WebDriverUtility {
	WebDriver driver;
	/**
	 * This method is used to open the browser according to the the value provided
	 * @param browser
	 * @return
	 */
	
	public WebDriver openTheBrowser(String browser) {
		switch (browser) {
		case "chrome":WebDriverManager.chromedriver().setup();
		 driver=new ChromeDriver();
			
			break;
		case "firefox":WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		break;

		case "ie":WebDriverManager.iedriver().setup();
		driver=new InternetExplorerDriver();
         break;
		default:System.out.println("Wrong key entered for browser");
			break;
		}
		return driver;
	}
	/**
	 * This method is used to setup the browser
	 */
	public void setupBrowser() {
		driver.manage().window().maximize();
		
	}
	/**
	 * This method is used for implicit wait
	 * @param timeout
	 */
	public void implicitWait(long timeout) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
		
	}
	/**
	 * This method is used to navigate the application
	 * @param url
	 */
	public void naviagteTheApplication(String url) {
		driver.get(url);
		
	}
	public void initializeAction(WebElement element) {
		 Actions a=new Actions(driver);
		a.moveToElement(element).perform();
	
		
	}
	

}
