package org.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.tyss.genericUtility.WebDriverUtility;

public class CommonPage {
	public CommonPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//img[@src='themes/softed/images/menuDnArrow.gif']")
	private WebElement moreTab;
	@FindBy(xpath="//a[.='Campaigns']")
	private WebElement campaignTab;
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
private WebElement administratorIcon;
	@FindBy(xpath="//a[.='Sign Out']")
	private WebElement signOut;

	//business library
 /**
  * This method is used to click on campaigns tab
  * @param webdriverutility
  */
	public void clickCampaign(WebDriverUtility webdriverutility)
	{
		webdriverutility.initializeAction(moreTab);
		campaignTab.click();
	}
	public void logoutAction(WebDriverUtility webdriverutility) {
		webdriverutility.initializeAction(administratorIcon);
		signOut.click();
	}
	
	
}
