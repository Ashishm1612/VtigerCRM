package org.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindAll;
public class LoginPage {
@FindAll({@FindBy(xpath="//input[@name='user_name']"),@FindBy(name="user_name")}) //it works as or operator,findbys will work as and operator
		private  WebElement userNameTextField;
@FindBy(xpath="//input[@name='user_password']")
private WebElement passwordTextField;
@FindBy(xpath="//input[@id='submitButton']")
private WebElement loginbtn;

public LoginPage(WebDriver driver)
{
	PageFactory.initElements(driver, this);
}
//business library
/**
 * This method is used to login to application
 * @param userName
 * @param password
 */
public void loginAction(String userName,String password)
{
	userNameTextField.sendKeys(userName);
	passwordTextField.sendKeys(password);
	loginbtn.click();
}

}
