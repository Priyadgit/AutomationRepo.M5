package organizationTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
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

public class CreateOrgTestWithBC extends BaseClass

{
	@Test
	public void CreateOrgTestWithBC () throws EncryptedDocumentException, IOException, InterruptedException
	{
		
				String ORGNAME = eUtil.ReadFromExcelFile("Contacts", 4, 3)+jUtil.getRandomNumber();
				System.out.println(ORGNAME);
				
				//Step 5 : Click on Organisations link
				OrganisationPage op = new OrganisationPage(driver);
				op.ClickOnCreateNewOrgBtn();
				System.out.println("Org page opened");
				
				//Step 6 : Create new Organization
				CreateNewOrgPage cnop = new CreateNewOrgPage(driver);
				Thread.sleep(1000);
				cnop.createNewOrg(ORGNAME);
				System.out.println("click on create new org button and add new org and save");
				//Step 7 : Validate
				OrgInfoPage oip = new OrgInfoPage(driver);
				String OrgHeader = oip.GetOrgHeader();
				/*if(OrgHeader.contains(ORGNAME))
				{
					System.out.println(OrgHeader);
					System.out.println("PASS");
				}
				else
				{
					System.out.println("FAIL");
				}*/
				Assert.assertTrue(OrgHeader.contains(ORGNAME));
				System.out.println(OrgHeader);
				System.out.println("PASS");
				
	}
}
