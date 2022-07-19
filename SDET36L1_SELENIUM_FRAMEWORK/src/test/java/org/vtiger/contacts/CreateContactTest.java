package org.vtiger.contacts;

import java.awt.Desktop.Action;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactTest {

	public static void main(String[] args) throws IOException {
		//object creation of generic Utility
		JavaUtility javaUtility= new JavaUtility();
		ExcelUtility excelUtility= new ExcelUtility();
		FileUtility fileUtility= new FileUtility();
		WebDriverUtility webDriverUtility=new WebDriverUtility();
		
		//getting random number
				int randomNumber=javaUtility.getRandomNumber();
		//initialize  Excel File
				excelUtility.initializeExcelFile(IConstants.VTIGEREXCELFILEPATH);
				
		//get data from Excel File
			String expected = excelUtility.getDataFromExcel(2, 1, "Contacts")+randomNumber;
		
		//to get the common data from property file
		fileUtility.initializePropertyFile(IConstants.VTIGERPROPERTYFILEPATH);
		
		
        String url = fileUtility.getDataFromProperty("url");
        String username = fileUtility.getDataFromProperty("username");
        String password = fileUtility.getDataFromProperty("password");
        String browser = fileUtility.getDataFromProperty("browser");
        String timeouts = fileUtility.getDataFromProperty("timeout");
        //convert string to long
        long timeout = javaUtility.convertStringToLong(timeouts);
        
        //run time polymorphism 
/*switch (browser) {
case "chrome":WebDriverManager.chromedriver().setup();
 driver=new ChromeDriver();
	
	break;
case "firefox":WebDriverManager.firefoxdriver().setup();
driver=new FirefoxDriver();

case "ie":WebDriverManager.iedriver().setup();
driver=new InternetExplorerDriver();

default:System.out.println("Wrong key entered for browser");
	break;
}*/
        
        //open the browser 
        WebDriver driver=webDriverUtility.openTheBrowser(browser);
        //driver.manage().window().maximize();
        //setup the browser
        webDriverUtility.setupBrowser();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
		webDriverUtility.implicitWait(timeout);
       // driver.get(url);
		webDriverUtility.naviagteTheApplication(url);
		//
		
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(expected );
		driver.findElement(By.xpath("(//input[@name='button'][1])[1]")).click();
		String actual = driver.findElement(By.id("dtlview_Last Name")).getText();
		System.out.println(actual);
		System.out.println(expected);
 		//validate the input is equal to output
		if(actual.equals(expected))
				{
			System.out.println("Pass");
				}
		else
			System.out.println("Fail");
		//mouse hover 
		WebElement signout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webDriverUtility.initializeAction(signout);
		//a.moveToElement(signout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		//close workbook
		excelUtility.workbookClose();
		//close driver
		driver.close();
		

		

		

		
	}

}
