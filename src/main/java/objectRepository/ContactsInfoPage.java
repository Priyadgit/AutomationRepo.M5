package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsInfoPage
{
	//Constructor creation
	public ContactsInfoPage (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Initialization
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement ContactHeaderText;

	public WebElement getContactHeaderText() {
		return ContactHeaderText;
	}
	/**
	 * This method returns the header text info from the contacts info page
	 * @return
	 */
	public String FetchContactHeaderInfo()
	{
		return ContactHeaderText.getText();
	}
}
