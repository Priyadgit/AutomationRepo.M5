package organizationTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import objectRepository.CreateNewOrgPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrgInfoPage;
import objectRepository.OrganisationPage;

public class CreateOrgTest {

	@Test
	public void CreateNewOrgTest() throws EncryptedDocumentException, IOException, InterruptedException
	{
		//Step 1 : Create Objects for all utilities
		ExcelFileUtility eUtil = new ExcelFileUtility();
		PropertyFileUtility pUtil = new PropertyFileUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		JavaUtility jUtil = new JavaUtility();
		WebDriver driver = null;
		//Step 2 : read all the required data
		//Test Data
		String ORGNAME = eUtil.ReadFromExcelFile("Contacts", 4, 3)+jUtil.getRandomNumber();
		System.out.println(ORGNAME);
		
		//Common Data
		String BROWSER = pUtil.ReadDatafromPropertyFile("browser");
		String URL = pUtil.ReadDatafromPropertyFile("url");
		String USERNAME = pUtil.ReadDatafromPropertyFile("username");
		String PASSWORD = pUtil.ReadDatafromPropertyFile("password");
		
		//Step 3: Launch the browser
	
		if(BROWSER.equalsIgnoreCase("Chrome"))
		{
			 driver=new ChromeDriver();
		}
		else 
		if (BROWSER.equalsIgnoreCase("Edge"))
		{
			 driver = new EdgeDriver();
		
		}
		else
		if(	BROWSER.equalsIgnoreCase("Firefox"))
		{
			 driver=new FirefoxDriver();
		}
		else
		{
			System.out.println("invalid browser");
		}
		
		
		wUtil.maximizeWindow(driver);
		wUtil.waitForPageLoad(driver);
		driver.get(URL);
		
		//Step 4 : Login into the application
		LoginPage lp = new LoginPage(driver);
		lp.LoginToApp(USERNAME, PASSWORD);
		
		//Step 5 : Click on Organisations link
		OrganisationPage op = new OrganisationPage(driver);
		op.ClickOnCreateNewOrgBtn();
		
		//Step 6 : Create new Organization
		CreateNewOrgPage cnop = new CreateNewOrgPage(driver);
		cnop.createNewOrg(ORGNAME);
		
		//Step 7 : Validate
		OrgInfoPage oip = new OrgInfoPage(driver);
		String OrgHeader = oip.GetOrgHeader();
		if(OrgHeader.contains(ORGNAME))
		{
			System.out.println(OrgHeader);
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FALSE");
		}
		
		//Step 8 : Logout of the application
		
		HomePage hp = new HomePage(driver);
		hp.LogoutOfApp(driver);
		
		System.out.println("Logout Successful");
		}

}
