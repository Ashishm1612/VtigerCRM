package org.vtiger.organizations;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationTest_Vtiger {

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
		      //setup the browser
		        webDriverUtility.setupBrowser();
		        webDriverUtility.implicitWait(timeout);
		        //
		        // driver.get(url);
				webDriverUtility.naviagteTheApplication(url);
	
	driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
	driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
	driver.findElement(By.xpath("//input[@id='submitButton']")).click();
	//login done
	
	String ActualInput = excelUtility.getDataFromExcel(2, 1, "Organization")+randomNumber;
	driver.findElement(By.xpath("//a[.='Organizations']")).click();
	driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
	driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(ActualInput);
	driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
	String ExpectedOutput = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
	System.out.println(ActualInput);
	System.out.println(ExpectedOutput);
	if(ActualInput.equals(ExpectedOutput))
	{
		System.out.println("test passed");
		
	}
	else
		System.out.println("test Failed");
	WebElement administratorlogo = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	webDriverUtility.initializeAction(administratorlogo);
	driver.findElement(By.xpath("//a[.='Sign Out']")).click();
	
	driver.quit();
	
	
	}

}
