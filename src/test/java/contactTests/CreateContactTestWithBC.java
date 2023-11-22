package contactTests;

import static org.testng.Assert.fail;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import objectRepository.ContactsInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;

@Listeners (genericUtilities.ListernersImplementationClass.class)
public class CreateContactTestWithBC extends BaseClass
{

	@Test (groups = "SmokeSuite")
	public void CreateContactTestWithBC() throws EncryptedDocumentException, IOException, InterruptedException
	{
		

		/* Test Data */
		String LASTNAME = eUtil.ReadFromExcelFile("Contacts", 1, 2);
		
		//Step 5 : Navigate to contacts link
		HomePage hp = new HomePage(driver);
		hp.ClickOnContactLnk(driver);
		
		Assert.fail();
		
		//Step 6 : Click on create contact lookup image
		ContactsPage cp = new ContactsPage(driver);
		cp.ClickOnContactsBtn();
		
		
		//Step 7 : Create new contact
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.CreateNewContact(LASTNAME);
		
		//Step 8 : Validate
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String contactHeader = cip.FetchContactHeaderInfo();
		Thread.sleep(1000);
		/*if(contactHeader.contains(LASTNAME))
		{
			System.out.println(contactHeader);
			System.out.println("pass");
		}
		else
		{
			System.out.println("fail");
		} */
		
		Assert.assertTrue(contactHeader.contains(LASTNAME));
		System.out.println(contactHeader);
		System.out.println("Pass");
	}
	
	@Test 
	public void demo() 
	{
		System.out.println("demo");
	}
}
