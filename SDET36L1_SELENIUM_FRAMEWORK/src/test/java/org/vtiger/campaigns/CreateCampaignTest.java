package org.vtiger.campaigns;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;
import org.vtiger.ObjectRepository.CommonPage;
import org.vtiger.ObjectRepository.CreateCampaign;
import org.vtiger.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignTest {


	

	public static void main(String[] args) {
		//object creation of generic Utility
				JavaUtility javaUtility= new JavaUtility();
				ExcelUtility excelUtility= new ExcelUtility();
				FileUtility fileUtility= new FileUtility();
				WebDriverUtility webDriverUtility=new WebDriverUtility();
				
				//getting random number
						int randomNumber=javaUtility.getRandomNumber();
				//initialize  Excel File
						excelUtility.initializeExcelFile(IConstants.VTIGEREXCELFILEPATH);
				
		//to get the common data from property file
				fileUtility.initializePropertyFile(IConstants.VTIGERPROPERTYFILEPATH);
				
				
		        String url = fileUtility.getDataFromProperty("url");
		        String username = fileUtility.getDataFromProperty("username");
		        String password = fileUtility.getDataFromProperty("password");
		        String browser = fileUtility.getDataFromProperty("browser");
		        String timeouts = fileUtility.getDataFromProperty("timeout");
		        //convert string to long
		        long timeout = javaUtility.convertStringToLong(timeouts);
					
		        //open the browser 
		        WebDriver driver=webDriverUtility.openTheBrowser(browser);
		        //driver.manage().window().maximize();
		        //setup the browser
		        webDriverUtility.setupBrowser();
		        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
				webDriverUtility.implicitWait(timeout);
		       // driver.get(url);
				webDriverUtility.naviagteTheApplication(url);
			      
				//creating object for POM classes
				LoginPage login=new LoginPage(driver);
				CommonPage common=new CommonPage(driver);
				CreateCampaign campaign=new CreateCampaign(driver);
				
				
				
				
				
				login.loginAction(username, password);//logging in
				
		 
		
		//driver.findElement(By.name("user_name")).sendKeys(username);
		//driver.findElement(By.name("user_password")).sendKeys(password);
		//driver.findElement(By.id("submitButton")).click();
		//login done
				common.clickCampaign(webDriverUtility);
		//WebElement more = driver.findElement(By.linkText("More"));
		//webDriverUtility.initializeAction(more);
		//a.moveToElement(more).perform();
		//driver.findElement(By.linkText("Campaigns")).click();
		campaign.clickOncreateCampaign(webDriverUtility);
		String actualInput = excelUtility.getDataFromExcel(2, 1, "Campaign")+randomNumber;;
		campaign.enterCampaignNameAndSave(actualInput);
		String expectedOutput = campaign.getCampaignName().trim();
		System.out.println(actualInput);
		System.out.println(expectedOutput);
		if(actualInput.equals(expectedOutput))
		{
			System.out.println("Pass");
		}
		else 
			System.out.println("Fail");
		
		common.logoutAction(webDriverUtility);
	//	Actions a=new Actions();
		//WebElement signout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		//webDriverUtility.initializeAction(signout);
		//driver.findElement(By.linkText("Sign Out")).click();
		excelUtility.workbookClose();
		driver.quit();
		
				}
				
	

	}


