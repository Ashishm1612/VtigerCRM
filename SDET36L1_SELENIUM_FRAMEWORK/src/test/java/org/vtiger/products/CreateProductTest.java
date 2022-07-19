package org.vtiger.products;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProductTest {

	public static void main(String[] args) {
		Random random= new Random();
		int randomNumber = random.nextInt(1000);
		String ActualInput = "rmgySDET36 "+randomNumber;
		
		WebDriverManager.chromedriver().setup();//path setup
		WebDriver driver=new ChromeDriver();//open browser
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/"); //open site
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		//login done
		driver.findElement(By.xpath("//a[.='Products']")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(ActualInput);
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		String ExpectedOutput = driver.findElement(By.xpath("//td[@id='mouseArea_Product Name']/span[@id='dtlview_Product Name']")).getText();
		System.out.println(ActualInput);
		System.out.println(ExpectedOutput);
  if(ActualInput.equals(ExpectedOutput))
	  System.out.println("Test pass");
  else
	  System.out.println("Test failed");
  Actions a= new Actions(driver);
  WebElement Administratorlogo = driver.findElement(By.xpath("//td[@class='small']/img[@src='themes/softed/images/user.PNG']"));
  a.moveToElement(Administratorlogo).perform();
  driver.findElement(By.xpath("//a[.='Sign Out']")).click();
  driver.quit();
		

	}

}
