package contactTests;

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
import objectRepository.ContactsInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.CreateNewOrgPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrgInfoPage;
import objectRepository.OrganisationPage;

public class CreateContactWithOrgTestBC extends BaseClass
{

		@Test
	public void CreateContactWithOrgTestBC() throws InterruptedException, EncryptedDocumentException, IOException
	{
		
		//Test Data
		String ORGNAME = eUtil.ReadFromExcelFile("Contacts", 4, 3)+jUtil.getRandomNumber();
		System.out.println(ORGNAME);
		String LASTNAME = eUtil.ReadFromExcelFile("Contacts", 4, 2);
		System.out.println(LASTNAME);
		
		
		//Step 5: Click on Organisation Link from Home Page
		HomePage hp = new HomePage(driver);
		hp.ClickOnOrgLnk(driver);
		
		//Step 6 : Click on create new Organisations button on Organisations page
		OrganisationPage op = new OrganisationPage(driver);
		op.ClickOnCreateNewOrgBtn();
		Thread.sleep(1000);
		
		//Step 7 : Create new Organization
		CreateNewOrgPage cnop = new CreateNewOrgPage(driver);
		cnop.createNewOrg(ORGNAME);
		
		//Step 8 : Validate
		OrgInfoPage oip = new OrgInfoPage(driver);
		String OrgHeader = oip.GetOrgHeader();
		/*if(OrgHeader.contains(ORGNAME))
		{
			System.out.println(OrgHeader);
			System.out.println("Organisation Created Successfully");
		}
		else
		{
			System.out.println("ORG FAIL");
		}*/
		
		//validation using assertion
		
		Assert.assertTrue(OrgHeader.contains(ORGNAME));
		System.out.println(OrgHeader);
		System.out.println("Organisation Created Successfully");
		
		//Step 9: Navigate to contacts link on Home Page
		hp.ClickOnContactLnk(driver);
		
		//Step 10 : click on Create new Contact button on Contacts page
		ContactsPage cp = new ContactsPage(driver);
		cp.ClickOnContactsBtn();
		
		//Step 11 : Create new Contact 
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.CreateNewContact(driver, LASTNAME, ORGNAME);
		
		//Step 12 : Validate Contact
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String ContactHeader = cip.FetchContactHeaderInfo();
		/*if(ContactHeader.contains(LASTNAME))
		{
			System.out.println(ContactHeader);
			System.out.println("Contact created successfully"); 
		}
		else
		{
			System.out.println("Contact Fail");
		}*/
		Assert.assertTrue(ContactHeader.contains(LASTNAME));
		System.out.println(ContactHeader);
		System.out.println("Contact created successfully"); 
	}

}
