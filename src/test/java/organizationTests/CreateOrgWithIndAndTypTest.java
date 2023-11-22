package organizationTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
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

public class CreateOrgWithIndAndTypTest {

	@Test
	public void CreateNewOrgWithIndAndTypTest() throws IOException, InterruptedException
	{
		//Step 1 : Create objects for all the utilities
				ExcelFileUtility eUtil = new ExcelFileUtility();
				PropertyFileUtility pUtil = new PropertyFileUtility();
				JavaUtility jUtil = new JavaUtility();
				WebDriverUtility wUtil = new WebDriverUtility();
				WebDriver driver = null;
				
				//Step 2 :Read from all the files
				
				//Common data from property file
				String BROWSER = pUtil.ReadDatafromPropertyFile("browser");
				String URL = pUtil.ReadDatafromPropertyFile("url");
				String USERNAME = pUtil.ReadDatafromPropertyFile("username");
				String PASSWORD = pUtil.ReadDatafromPropertyFile("password");
				
				//Test data from excel file
				String ORGNAME = eUtil.ReadFromExcelFile("Organisations", 7, 2)+jUtil.getRandomNumber();
				String INDNAME = eUtil.ReadFromExcelFile("Organisations", 7, 3);
				String INDTYP = eUtil.ReadFromExcelFile("Organisations", 7, 4);
				System.out.println(ORGNAME);
				System.out.println(INDNAME);
				System.out.println(INDTYP);
				
				if(BROWSER.equalsIgnoreCase("Chrome"))
				{
					driver=new ChromeDriver();
				}
				else if (BROWSER.equalsIgnoreCase("Edge"))
				{
					driver = new EdgeDriver();
				}
				else if (BROWSER.equalsIgnoreCase("Firefox"))
				{
					driver = new FirefoxDriver();
				}
				else
				{
					System.out.println("Invalid browser");
				}
				
				//Step 3 :Launch the browser
				wUtil.maximizeWindow(driver);
				wUtil.waitForPageLoad(driver);
				driver.get(URL);
				
				//Step 4 : Login into the application
				LoginPage lp = new LoginPage(driver);
				lp.LoginToApp(USERNAME, PASSWORD);
				
				//Step 5 : Click on Org link from Home Page
				HomePage hp = new HomePage(driver);
				hp.ClickOnOrgLnk(driver);
				
				//Step 6 : click on create new org button
				OrganisationPage op = new OrganisationPage(driver);
				op.ClickOnCreateNewOrgBtn();
				
				//Step 7 : Create new Org with Ind and Type dropdown
				CreateNewOrgPage cnop = new CreateNewOrgPage(driver);
				cnop.createNewOrg(ORGNAME, INDNAME,INDTYP);
				
				//Step 8 : Validate
				OrgInfoPage oip = new OrgInfoPage(driver);
				String OrgHeader = oip.GetOrgHeader();
				
				if (OrgHeader.contains(ORGNAME))
				{
					System.out.println(OrgHeader);
					System.out.println("Pass");
				}
				else
				{
					System.out.println("Fail");
				}
				
				//Step 9 : Logout of the application
				
				hp.LogoutOfApp(driver);
				System.out.println("Logout Successful");
				

	}

}
