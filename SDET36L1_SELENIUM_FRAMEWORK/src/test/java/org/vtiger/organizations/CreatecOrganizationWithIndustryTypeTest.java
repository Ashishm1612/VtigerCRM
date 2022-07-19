package org.vtiger.organizations;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreatecOrganizationWithIndustryTypeTest {

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
		
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		//login done
		String actualInput = excelUtility.getDataFromExcel(2, 1, "Organization")+randomNumber; //get data from Excel File
		driver.findElement(By.xpath("//a[.='Organizations']")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(actualInput);
		WebElement industryList = driver.findElement(By.xpath("//select[@name='industry']"));
		Select s= new Select(industryList);
		s.selectByValue("Education");
		WebElement accountType = driver.findElement(By.xpath("//select[@name='accounttype']"));
		Select s1= new Select(accountType);
		s1.selectByValue("Press");
		String actualIndustry =excelUtility.getDataFromExcel(4, 2, "Organization") ;
		String actualType =excelUtility.getDataFromExcel(4, 3, "Organization") ; ;
		driver.findElement(By.xpath("//input[@value='T']")).click();
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		String expectedOutput = driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']/span[@id='dtlview_Organization Name']")).getText();
		String expectedIndustry = driver.findElement(By.xpath("//td[@id='mouseArea_Industry']/span[@id='dtlview_Industry']")).getText();
		String expectedType = driver.findElement(By.xpath("//span[@id='dtlview_Type']")).getText();



if(actualInput.equals(expectedOutput)&&actualType.equals(expectedType)&&actualIndustry.equals(expectedIndustry))
		{
	System.out.println("Test passed");
		}
else
	System.out.println("Test failed");
		

//mouse hover 
	
		WebElement signout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webDriverUtility.initializeAction(signout);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.close();

		
		


	}

}
