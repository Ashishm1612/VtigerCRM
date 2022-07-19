package org.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.WebDriverUtility;

public class CreateCampaign {
@FindBy(xpath="//img[@title='Create Campaign...']")
private WebElement campaigncreate;
@FindBy(xpath="//input[@name='campaignname']")
private WebElement campaignnameTextfield;
@FindBy(xpath="//input[@class='crmButton small save']")
private WebElement saveBtn;
@FindBy(xpath="//td[@id='mouseArea_Campaign Name']")
private WebElement campaignInformation_name;

public CreateCampaign(WebDriver driver)
{
	PageFactory.initElements(driver, this);
}

//business library
/**
 * This method is used to click on create campaign button
 * @param webDriverUtility
 */
public void clickOncreateCampaign(WebDriverUtility webDriverUtility)
{
	campaigncreate.click();
}
/**
 * This method is used to enter camapaign name and click on save button
 * @param actualInput
 */
public void enterCampaignNameAndSave(String actualInput)
{
	campaignnameTextfield.sendKeys(actualInput);
	saveBtn.click();
}
public String getCampaignName() {
	return campaignInformation_name.getText();
}

}