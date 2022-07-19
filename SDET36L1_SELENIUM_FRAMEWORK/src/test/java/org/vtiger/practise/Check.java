package org.vtiger.practise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Check {

	public static void main(String[] args) {
		WebDriver driver = null;
String text = driver.findElement(By.xpath("//input[@value='80']")).getText();
System.out.println(text);
	}

}
