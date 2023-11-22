package contactTests;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
import objectRepository.ContactsInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;

public class CreateContactTest {

	@Test
	public void CreateNewContactTest() throws IOException, InterruptedException
	
	{
				//Step 1 : Create objects of all Utilities
		
		
				ExcelFileUtility eUtil = new ExcelFileUtility();
				PropertyFileUtility pUtil = new PropertyFileUtility();
				WebDriverUtility wUtil = new WebDriverUtility();
				JavaUtility jUtil = new JavaUtility();
				WebDriver driver = null;

				// Step 2: Read all the required Data
				/* Common Data */
				String BROWSER = pUtil.ReadDatafromPropertyFile("browser");
				String URL = pUtil.ReadDatafromPropertyFile("url");
				String USERNAME = pUtil.ReadDatafromPropertyFile("username");
				String PASSWORD = pUtil.ReadDatafromPropertyFile("password");

				/* Test Data */
				String LASTNAME = eUtil.ReadFromExcelFile("Contacts", 1, 2);

				// Step 3: Launch the browser - PolyMorphism - Run Time - Driver
				if (BROWSER.equalsIgnoreCase("Chrome"))// true f
				{
					
					driver = new ChromeDriver();
				} else if (BROWSER.equalsIgnoreCase("Firefox")) // t f
				{
					
					driver = new FirefoxDriver();
				} else if (BROWSER.equalsIgnoreCase("Edge"))// f
				{
					
					driver = new EdgeDriver();
				} else {
					System.out.println("invalid Browser name");
				}

				wUtil.maximizeWindow(driver);
				wUtil.waitForPageLoad(driver);
				driver.get(URL);
				
				//Step 4 : login into tha application
				LoginPage lp = new LoginPage(driver);
				lp.LoginToApp(USERNAME, PASSWORD);
				
				//Step 5 : Navigate to contacts link
				HomePage hp = new HomePage(driver);
				hp.ClickOnContactLnk(driver);
				
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
				if(contactHeader.contains(LASTNAME))
				{
					System.out.println(contactHeader);
					System.out.println("pass");
				}
				else
				{
					System.out.println("fail");
				}
				
				Thread.sleep(1000);
				//Step 9 : Logout
				hp.LogoutOfApp(driver);
				
				System.out.println("Logout successful");
				
				//Step 10 : Close the browser
				//driver.quit();
	}

}
