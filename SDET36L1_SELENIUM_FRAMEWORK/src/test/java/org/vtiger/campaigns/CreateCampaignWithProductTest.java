package org.vtiger.campaigns;

import java.time.Duration;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;
import org.vtiger.ObjectRepository.LoginPage;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignWithProductTest {

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
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
		webDriverUtility.implicitWait(timeout);
       // driver.get(url);
		webDriverUtility.naviagteTheApplication(url);
		//creating object for POM classes
		LoginPage login=new LoginPage(driver);
		login.loginAction(username, password);
		
	    //driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		//driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
		//driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		//login done
		//get data from Excel File
		String actualInputProduct = excelUtility.getDataFromExcel(2, 1, "Product")+randomNumber;
		
 
		//product creation
		driver.findElement(By.xpath("//a[.='Products']")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(actualInputProduct);
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		//campaign creation
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='lvtHeaderText']"))));
		String actualInputCampaign =excelUtility.getDataFromExcel(2, 1, "Campaign")+randomNumber;
		// action start
		
		WebElement more= driver.findElement(By.xpath("//img[@src='themes/softed/images/menuDnArrow.gif']"));
		webDriverUtility.initializeAction(more);
		driver.findElement(By.xpath("//a[.='Campaigns']")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.xpath("//input[@name='campaignname']")).sendKeys(actualInputCampaign);
		driver.findElement(By.xpath("//td[@class='dvtCellLabel']/following-sibling::td/img[@alt='Select']")).click();
		Set<String> allwh = driver.getWindowHandles();
		for(String wh:allwh)
		{    driver.switchTo().window(wh);
		if(driver.getCurrentUrl().contains("Products&action"))
			break;
		}
		driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(actualInputProduct);
		driver.findElement(By.xpath("//input[@class='crmbutton small create']")).click();
		driver.findElement(By.xpath("//a[.='"+actualInputProduct +"']")).click();

		Set<String> allwh2 = driver.getWindowHandles();
		for(String wh:allwh2)
		{    driver.switchTo().window(wh);
		if(driver.getCurrentUrl().contains("Campaigns&action"))
			break;
		}
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='dvHeaderText']"))));
		String expectedCampaignOutput = driver.findElement(By.xpath("//td[@id='mouseArea_Campaign Name']")).getText().trim();
		String expectedProductOutput = driver.findElement(By.xpath("//td[@id='mouseArea_Product']")).getText().trim();
		
		if(actualInputProduct.equals(expectedProductOutput)&&actualInputCampaign.equals(expectedCampaignOutput))
		{
			System.out.println("Test Passed");
		}
		else
			System.out.println("Test Failed");
		WebElement signout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webDriverUtility.initializeAction(signout);
		
		driver.findElement(By.linkText("Sign Out")).click();
		//close workbook
		//workbook.close();
		excelUtility.workbookClose();
		//close driver
		driver.close();
	}
}
