package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreateNewOrgPage extends WebDriverUtility
{
	public CreateNewOrgPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@name='accountname']")
	private WebElement OrgNameEdt;
	
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]")
	private WebElement SaveBtn;
	
	@FindBy(xpath="//select[@name='industry']")
	private WebElement Inddropdown;
	
	public WebElement getOrgNameEdt() {
		return OrgNameEdt;
	}

	public WebElement getInddropdown() {
		return Inddropdown;
	}

	@FindBy(xpath="//select[@name='accounttype']")
	private WebElement Typdropdown;

	public WebElement getTypdropdown() {
		return Typdropdown;
	}
	
	public WebElement getSaveBtn() {
		return SaveBtn;
	}
	
	/**
	 * This method will create organisation with mandatory fields
	 * @param driver
	 * @param ORGNAME
	 */
	public void createNewOrg(String ORGNAME) 
	{
		OrgNameEdt.sendKeys(ORGNAME);
		SaveBtn.click();
	}
/**
 * this menthod will create nwe Organisation with industry dropdown
 * @param ORGNAME
 * @param INDNAME
 */
	public void createNewOrg( String ORGNAME, String INDNAME)
	{
		OrgNameEdt.sendKeys(ORGNAME);
		handleDropdown(INDNAME, Inddropdown);
		SaveBtn.click();
	}
	/**
	 * This method will create new organisation with industry dropdown and type dropdown
	 * @param ORGNAME
	 * @param INDNAME
	 * @param INDTYPE
	 */
	public void createNewOrg( String ORGNAME, String INDNAME,String INDTYPE)
	{
		OrgNameEdt.sendKeys(ORGNAME);
		handleDropdown(INDNAME, Inddropdown);
		handleDropdown(INDTYPE, Typdropdown);
		SaveBtn.click();
	}
	
	
}
