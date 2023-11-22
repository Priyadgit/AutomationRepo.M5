package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage 
{
	//Constructor creation
	public ContactsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Initialization
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement ContactsLnk;
	
	//getter method to access private elements
	public WebElement getContactsLnk() 
	{
		return ContactsLnk;
	}
	/**
	 * This method clicks on Contacts link from the Home Page
	 */
	public void ClickOnContactsBtn()
	{
		ContactsLnk.click();
	}
}
