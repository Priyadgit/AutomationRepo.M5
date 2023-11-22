package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class HomePage extends WebDriverUtility
{
	public HomePage ( WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	//declaration
	//Contacts link
	@FindBy (xpath="//a[text()='Contacts']")
	private WebElement ContactsLnk;
	
	//Organization link
	@FindBy (xpath="(//a[text()='Organizations'])[1]")
	private WebElement OrgLnk;
	
	@FindBy (xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminimg;
	
	@FindBy( xpath = "//a[text()='Sign Out']")
	private WebElement signoutBtn;
	
	
	public WebElement getContactsLnk() {
		return ContactsLnk;
	}



	public WebElement getOrgLnk() {
		return OrgLnk;
	}

	public WebElement getAdminimg() {
		return adminimg;
	}

	public WebElement getSignoutBtn() {
		return signoutBtn;
	}

	////Generic Library - click on contacts link
	public void ClickOnContactLnk(WebDriver driver)
	{
		ContactsLnk.click();
	}
	
	public void ClickOnOrgLnk(WebDriver driver)
	{
		OrgLnk.click();
	}
	
	public void LogoutOfApp(WebDriver driver) throws InterruptedException
	{
		mouseHoverAction(adminimg , driver);
		Thread.sleep(1000);
		signoutBtn.click();
	}
}
