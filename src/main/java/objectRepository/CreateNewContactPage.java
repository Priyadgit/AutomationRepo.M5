package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreateNewContactPage extends WebDriverUtility
{
	//Constructor creation
	public CreateNewContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//initialization
	@FindBy(name ="lastname")
	private WebElement LastnameEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;
	
	@FindBy(xpath="//img[@src='themes/softed/images/select.gif']")
	private WebElement OrgLookUpImg;
	
	@FindBy(xpath="//input[@id='search_txt']")
	private WebElement OrgSearchEdt;
	
	@FindBy(xpath="//input[@name='search']")
	private WebElement OrgSearchBtn;

	//getter methods
	public WebElement getLastnameEdt() {
		return LastnameEdt;
	}

	public WebElement getSaveBtn() {
		return SaveBtn;
	}

	public WebElement getOrgLookUpImg() {
		return OrgLookUpImg;
	}

	public WebElement getOrgSearchEdt() {
		return OrgSearchEdt;
	}

	public WebElement getOrgSearchBtn() {
		return OrgSearchBtn;
	}
	
	//business libraries
	
	public void CreateNewContact(String LASTNAME)
	{
		LastnameEdt.sendKeys(LASTNAME);
		SaveBtn.click();
	}
	/**
	 * This method creates a new contact with lastname and orgname info on Create new Contacts page
	 * @param driver
	 * @param LASTNAME
	 * @param ORGNAME
	 */
	public void CreateNewContact(WebDriver driver , String LASTNAME, String ORGNAME)
	{
		LastnameEdt.sendKeys(LASTNAME);
		OrgLookUpImg.click();
		switchToWindow(driver, "Accounts");
		OrgSearchEdt.sendKeys(ORGNAME);
		OrgSearchBtn.click();
		driver.findElement(By.xpath("//a[text()='"+ORGNAME+"']")).click();
		switchToWindow(driver, "Contacts");
		SaveBtn.click();
		
	 }
}
