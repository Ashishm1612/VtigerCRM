package org.vtiger.contacts;

import java.time.Duration;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;
import org.vtiger.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrganizationTest {

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
				
		
			//String expected = excelUtility.getDataFromExcel(2, 1, "Contacts")+randomNumber;
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
				//creating object for POM classes
				LoginPage login=new LoginPage(driver);
				login.loginAction(username, password);
				
		//driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		//driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
		//driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		//login done
		String actualOrganization =excelUtility.getDataFromExcel(2, 1, "Organization")+randomNumber; //get data from Excel File
		driver.findElement(By.xpath("//a[.='Organizations']")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(actualOrganization);
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		//contact creation
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='dvHeaderText']"))));
		driver.findElement(By.xpath("//a[.='Contacts']")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		String actualContact =excelUtility.getDataFromExcel(2, 1, "Contacts")+randomNumber;  //get data from Excel File
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(actualContact);
		driver.findElement(By.xpath("//td[contains(.,'Organization Name')]/following-sibling::td/img[@alt='Select']")).click();
		Set<String> allwh = driver.getWindowHandles();
		for(String wh:allwh)
		{
			driver.switchTo().window(wh);
			if(driver.getCurrentUrl().contains("Accounts&action"))
				break;
		}
		driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(actualOrganization);
		driver.findElement(By.xpath("//input[@class='crmbutton small create']")).click();
		driver.findElement(By.xpath("//a[.='"+actualOrganization+"']")).click();
		Set<String> allwh2 = driver.getWindowHandles();
		for(String wh:allwh2)
		{
			driver.switchTo().window(wh);
			if(driver.getCurrentUrl().contains("Contacts&action"))
				break;
		}
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		String expectedContact = driver.findElement(By.xpath("//td[@id='mouseArea_Last Name']")).getText().trim();
		String expectedOrganization = driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']")).getText().trim();
		if(expectedContact.equals(actualContact)&&expectedOrganization.equals(actualOrganization))
		{
			System.out.println("Test passed");
			
		}
		else
			System.out.println("Test failed");
		WebElement signout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webDriverUtility.initializeAction(signout);
		//a.moveToElement(signout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		//close workbook
		//workbook.close();
		excelUtility.workbookClose();
		//close driver
		driver.close();



		






		
		

	}

}
