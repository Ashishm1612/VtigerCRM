package org.vtiger.documents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;

public class CreateDocumentTest {

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
			      //open the browser 
			        WebDriver driver=webDriverUtility.openTheBrowser(browser);
			        //driver.manage().window().maximize();
			        //setup the browser
			        webDriverUtility.setupBrowser();
			        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
					webDriverUtility.implicitWait(timeout);
			       // driver.get(url);
					webDriverUtility.naviagteTheApplication(url);
					String actualTitleInput = excelUtility.getDataFromExcel(2, 1, "Document")+randomNumber;
					
					driver.findElement(By.name("user_name")).sendKeys(username);
					driver.findElement(By.name("user_password")).sendKeys(password);
					driver.findElement(By.id("submitButton")).click();
					//login done
					String actualNoteInput = excelUtility.getDataFromExcel(2, 2,"Document");
					driver.findElement(By.xpath("//a[contains(.,'Documents')]")).click();
					driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
					driver.findElement(By.xpath("//input[@name='notes_title']")).sendKeys(actualTitleInput);
					driver.switchTo().frame(driver.findElement(By.xpath("//iframe")));
					driver.findElement(By.xpath("//body[@class='cke_show_borders']")).sendKeys(actualNoteInput);
					String path = excelUtility.getDataFromExcel(2, 3, "Document");
					String absolutePath = System.getProperty("user.dir")+path;
					driver.switchTo().defaultContent();
					driver.findElement(By.xpath("//input[@id='filename_I__']")).sendKeys(absolutePath);
					driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
					WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
					wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='dvHeaderText']"))));
					String expectedTitleOutput = driver.findElement(By.xpath("//td[@id='mouseArea_Title']")).getText();
					expected


					

	}

}
