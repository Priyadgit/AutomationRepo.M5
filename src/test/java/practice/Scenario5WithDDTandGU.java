package practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;

public class Scenario5WithDDTandGU 
{

	
		public static void main(String[] args) throws InterruptedException, IOException {

			// Create Object of all Utilities
			ExcelFileUtility eUtil = new ExcelFileUtility();
			PropertyFileUtility pUtil = new PropertyFileUtility();
			WebDriverUtility wUtil = new WebDriverUtility();
			JavaUtility jUtil = new JavaUtility();
			WebDriver driver = null;

			// Step 1: Read all the required Data
			/* Common Data */
			String BROWSER = pUtil.ReadDatafromPropertyFile("browser");
			String URL = pUtil.ReadDatafromPropertyFile("url");
			String USERNAME = pUtil.ReadDatafromPropertyFile("username");
			String PASSWORD = pUtil.ReadDatafromPropertyFile("password");

			/* Test Data */
			String LASTNAME = eUtil.ReadFromExcelFile("Contacts", 4, 2);
			String ORGNAME = eUtil.ReadFromExcelFile("Contacts", 4, 3)+jUtil.getRandomNumber();

			// Step 2: Launch the browser - PolyMorphism - Run Time - Driver
			if (BROWSER.equalsIgnoreCase("Chrome"))// true f
			{
				 driver  = new ChromeDriver();
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

			// Step 3: Login to Application
			driver.findElement(By.name("user_name")).sendKeys(USERNAME);
			driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			driver.findElement(By.id("submitButton")).click();

			//Step 4: Navigate to Organizations
			driver.findElement(By.linkText("Organizations")).click();
			
			//Step 5: Click on Create Organization look up Imge
			driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
			
			//Step 6: Create Organization With Mnadatory fields
			driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
			
			//Step 7: Save
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			//Step 8: Validate
			String orgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(orgHeader.contains(ORGNAME))
			{
				System.out.println(orgHeader);
				System.out.println("Organization created");
			}
			else
			{
				System.out.println("FAIL");
			}
			
			
			// Step 9: navigate to Contacts link
			driver.findElement(By.linkText("Contacts")).click();

			// Step 10: click on create contact look Up Image
			driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();

			// Step 11: create contact with mandatory fields
			driver.findElement(By.name("lastname")).sendKeys(LASTNAME);

			// Step 12: Click on Org look Up Image
			driver.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();

			//Step 13: switch the control to child window
			wUtil.switchToWindow(driver, "Accounts");
			System.out.println("switched to child window");

			// Step 14: search for Organization //infosys515 infosys1000
			driver.findElement(By.name("search_text")).sendKeys(ORGNAME);
			driver.findElement(By.name("search")).click();
			driver.findElement(By.xpath("//a[.='"+ORGNAME+"']")).click();
			                             //a[.='infosys515']
			                             //a[.='infosys1000']
			
			//driver.findElement(By.linkText(ORGNAME));
			
			// Step 15: swicth the control back to parent
			wUtil.switchToWindow(driver, "Contacts");
			System.out.println("swicthed back to parent");

			// Step 12: save
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

			// Step 13: Validate
			String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if (contactHeader.contains(LASTNAME)) {
				System.out.println(contactHeader);
				System.out.println("PASS");
			} else {
				System.out.println("FAIL");
			}

			// Step 14: Logout
			WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));

			wUtil.mouseHoverAction(ele, driver);
			Thread.sleep(1000);
			driver.findElement(By.linkText("Sign Out")).click();
			System.out.println("Logout is successfull");

		}

	
	}


