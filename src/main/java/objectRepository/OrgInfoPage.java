package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class OrgInfoPage extends WebDriverUtility
{
	public OrgInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement OrgHeaderText;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminimg;
	
	@FindBy(xpath="//a[text()='Sign Out']")
	private WebElement SignoutLnk;

	public WebElement getOrgInfoText() {
		return OrgHeaderText;
	}

	public WebElement getadminimg() {
		return adminimg;
	}

	public WebElement getSignoutLnk() {
		return SignoutLnk;
	}
	
	/**
	 * This method will capture the header text and return it to the caller 
	 * @param driver
	 * @throws InterruptedException
	 */
	public String GetOrgHeader() 
	{
		return OrgHeaderText.getText();
		
	}

}
