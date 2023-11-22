package genericUtilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import objectRepository.HomePage;
import objectRepository.LoginPage;

/**
 * This class consists of basic configuration annotations of TestNG
 * @author Priya H.M
 */
public class BaseClass {
	
	public PropertyFileUtility pUtil = new PropertyFileUtility();
	public ExcelFileUtility eUtil = new ExcelFileUtility();
	public WebDriverUtility wUtil = new WebDriverUtility();
	public JavaUtility jUtil = new JavaUtility();
	public WebDriver driver;
	
	public static WebDriver sdriver;
	
	@BeforeSuite(alwaysRun = true)
	public void bsConfig()
	{
		System.out.println("==DB connection Successful==");
	}
	
	@BeforeClass(alwaysRun = true)
	public void bcConfig() throws IOException
	{
		String BROWSER = pUtil.ReadDatafromPropertyFile("browser");
		String URL = pUtil.ReadDatafromPropertyFile("url");
		
		if (BROWSER.equalsIgnoreCase("chrome"))
		{
			 driver = new ChromeDriver();
			 System.out.println(BROWSER+" browser launched");
		}
		else if (BROWSER.equalsIgnoreCase("Edge"))
		{
			 driver = new EdgeDriver();
			 System.out.println(BROWSER+" browser launched");
		}
		else if (BROWSER.equalsIgnoreCase("Firefox"))
		{
			 driver = new FirefoxDriver();
			 System.out.println(BROWSER+" browser launched");
		}
		else
		{
			System.out.println("invalid browser name");
		}
		
		wUtil.maximizeWindow(driver);
		wUtil.waitForPageLoad(driver);
		driver.get(URL);
		sdriver=driver;
	}
		
	@BeforeMethod(alwaysRun = true)
	public void bmConfig() throws IOException
	{
		String USERNAME = pUtil.ReadDatafromPropertyFile("username");
		String PASSWORD = pUtil.ReadDatafromPropertyFile("password");
		
		LoginPage lp = new LoginPage(driver);
		lp.LoginToApp(USERNAME, PASSWORD);
		System.out.println("==Login Successful==");
		
		
	}
		
	@AfterMethod(alwaysRun = true)
	public void amConfig() throws InterruptedException
	{
		HomePage hp = new HomePage(driver);
		hp.LogoutOfApp(driver);
		System.out.println("==Logout Successful==");
	}
	
	@AfterClass(alwaysRun = true)
	public void acConfig()
	{
		driver.quit();
		System.out.println("==Browser closed successfully==");
	}
	
	@AfterSuite(alwaysRun = true)
	public void asConfig()
	{
		System.out.println("==DB closed successful==");
	}
	

}
