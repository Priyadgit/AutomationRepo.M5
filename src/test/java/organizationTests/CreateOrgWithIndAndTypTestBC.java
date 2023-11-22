package organizationTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import objectRepository.CreateNewOrgPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrgInfoPage;
import objectRepository.OrganisationPage;

public class CreateOrgWithIndAndTypTestBC extends BaseClass
{
	@Test
	public void CreateOrgWithIndAndTypTestBC() throws IOException
	{
		
		
		//Test data from excel file
		String ORGNAME = eUtil.ReadFromExcelFile("Organisations", 7, 2)+jUtil.getRandomNumber();
		String INDNAME = eUtil.ReadFromExcelFile("Organisations", 7, 3);
		String INDTYP = eUtil.ReadFromExcelFile("Organisations", 7, 4);
		System.out.println(ORGNAME);
		System.out.println(INDNAME);
		System.out.println(INDTYP);
		
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
		
		/*if (OrgHeader.contains(ORGNAME))
		{
			System.out.println(OrgHeader);
			System.out.println("Pass");
		}
		else
		{
			System.out.println("Fail");
		}*/
		Assert.assertTrue(OrgHeader.contains(ORGNAME));
		System.out.println(OrgHeader);
		System.out.println("Pass");
		
	}

}
