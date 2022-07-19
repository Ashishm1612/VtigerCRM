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

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidateDatabasewrtoGUI_rmgyantraTest {

	public static void main(String[] args) {
		Random random=new Random();
		int randomNumber=random.nextInt(1000);
		String expectedProjectName = "SDET36"+randomNumber;
		System.out.println("Expected Project name is " +expectedProjectName);
		try {
			Driver dbdriver=new Driver();
			DriverManager.registerDriver(dbdriver);
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root","root");
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate("insert into project values('TY_PROJ"+randomNumber+"','Ashish','23/06/2022','"+expectedProjectName+"','Still not completed',7);");
			System.out.println("project added into db successfully");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		System.out.println("Browser lauunched");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8084/");
		driver.findElement(By.xpath("//input[@id='usernmae']")).sendKeys("rmgyantra");
		driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[@class='btn btn-lg btn-primary btn-block text-uppercase']")).click();
		driver.findElement(By.xpath("//a[.='Projects']")).click();
		List<WebElement> listOfProjects = driver.findElements(By.xpath("//table[@class='table table-striped table-hover']/tbody//tr/td[2]"));
		for(WebElement project:listOfProjects)
		{
			String actualProjectName = project.getText();
			if(actualProjectName.equals(expectedProjectName))
			{	System.out.println("project is present in project list");
			System.out.println("Actual project name -->>>>"+ actualProjectName);
		}
			break;
		}
		driver.quit();
	}

}
