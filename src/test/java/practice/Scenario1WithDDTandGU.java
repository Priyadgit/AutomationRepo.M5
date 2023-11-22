package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;

public class Scenario1WithDDTandGU {

	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException
	{
		
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
		String LASTNAME = eUtil.ReadFromExcelFile("Contacts", 1, 2);

		// Step 2: Launch the browser - PolyMorphism - Run Time - Driver
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
		
		//Step 3 : Create Contact
		
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		Thread.sleep(2000);
		
		//Step 4 : Provide lastname
		
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(LASTNAME);
		
		//Step 5 : Save
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		
		//Step 6 : Validation
		String text = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(text.contains(LASTNAME))
		{
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FAIL");
		}
		
		//Step 7 : Signout
		
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(ele);
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//td[@class='small' and @valign='bottom'])[1]")).click();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		System.out.println("Sign out Successful");	

	}

}
