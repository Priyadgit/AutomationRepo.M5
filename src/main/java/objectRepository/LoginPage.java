package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	//Declaration

	@FindBy (name ="user_name")
	private WebElement UserNameEdt;
	
	@FindBy(name = "user_password")
	private WebElement PasswordEdt;
	
	@FindBy(id = "submitButton")
	private WebElement LoginBtn;
	
	//initialization
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	//utilization
	public WebElement getUserNameEdt() {
		return UserNameEdt;
	}
	public WebElement getPasswordEdt() {
		return PasswordEdt;
	}
	public WebElement getLoginBtn() {
		return LoginBtn;
	}
	//4 - Utilization
	
	//Generic Library
	
	public void LoginToApp(String USERNAME , String PASSWORD)
	{
		UserNameEdt.sendKeys(USERNAME);
		PasswordEdt.sendKeys(PASSWORD);
		LoginBtn.click();
	}
	}


