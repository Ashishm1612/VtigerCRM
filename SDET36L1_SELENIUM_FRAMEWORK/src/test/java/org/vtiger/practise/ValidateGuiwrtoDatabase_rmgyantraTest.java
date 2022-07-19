package org.vtiger.practise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidateGuiwrtoDatabase_rmgyantraTest {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		Random random= new Random();
		int randomNumber = random.nextInt(1000);
		String ProjectName = "SDET36"+randomNumber;
		driver.get("http://localhost:8084/");
		driver.findElement(By.xpath("//input[@id='usernmae']")).sendKeys("rmgyantra");
		driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[@class='btn btn-lg btn-primary btn-block text-uppercase']")).click();
		driver.findElement(By.xpath("//a[.='Projects']")).click();
		driver.findElement(By.xpath("//span[.='Create Project']")).click();
		driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys("ProjectName");
		driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("Deepak");
		WebElement projectStatusDropDown = driver.findElement(By.xpath("//label[.='Project Status ']/following-sibling::select"));
		Select select=new Select(projectStatusDropDown);
		select.selectByVisibleText("On Goging");
		driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();
		driver.quit();


		try {
			Driver dbdriver=new Driver();
			DriverManager.registerDriver(dbdriver);
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root","root");
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from project;");
			while(result.next())
			{
				if(result.getString("project_name").equals(ProjectName))
					System.out.println("project is present");
				System.out.println("Actual project name is "+result.getString("project_name"));
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}

}
