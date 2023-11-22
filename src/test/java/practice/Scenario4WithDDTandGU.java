package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

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
import org.openqa.selenium.support.ui.Select;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;

public class Scenario4WithDDTandGU {

	public static void main(String[] args) throws InterruptedException, IOException 
	{
		//Step 1 : Create objects for all utilities
		
		 ExcelFileUtility eUtil = new ExcelFileUtility();
		 PropertyFileUtility pUtil = new PropertyFileUtility();
		 JavaUtility jUtil = new JavaUtility();
		 WebDriverUtility wUtil = new WebDriverUtility();
		 
		 //Step 2 : Read all required data
		 //common data from property file
		 
		 String BROWSER = pUtil.ReadDatafromPropertyFile("browser");
		 String URL = pUtil.ReadDatafromPropertyFile("url");
		 String USERNAME = pUtil.ReadDatafromPropertyFile("username");
		 String PASSWORD = pUtil.ReadDatafromPropertyFile("password");
		
		 //Test data from excel file
		 
		 String LASTNAME = eUtil.ReadFromExcelFile("Contacts", 1, 2);
		 String ORGNAME = eUtil.ReadFromExcelFile("Organisations", 1, 2)+jUtil.getRandomNumber();
		 String INDNAME = eUtil.ReadFromExcelFile("Organisations", 4, 3);
		 String INDTYPE = eUtil.ReadFromExcelFile("Organisations", 7, 4);
		 
		 WebDriver driver = null;
		
		//Step 3 : Launch the browser
		
		if (BROWSER.equalsIgnoreCase("Chrom"))
		{
			 driver = new ChromeDriver();
		}
		else if (BROWSER.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		else if (BROWSER.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("invalid browser name");
		}
		
		//Step 4 : Launch URL
		
		wUtil.maximizeWindow(driver);
		wUtil.waitForPageLoad(driver);
		driver.get(URL);
		
		//step 5 : Login to the application
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		
		//Step 6: Create Organization with Industry name and Type dropdown
		
		//Step 7: Navigate to Organizations
		driver.findElement(By.xpath("//a[text()='Organizations'][1]")).click();
		Thread.sleep(1000);
		
		//Step 8: Click on Create Organization look up Image
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		Thread.sleep(1000);
		
		//Step 9: Create Organization With Mnadatory fields
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(ORGNAME);
		Thread.sleep(1000);
		
		//Step 10: industry dropdown
		WebElement dd = driver.findElement(By.xpath("//select[@name='industry']"));
		Select sel = new Select(dd);
		sel.selectByVisibleText(INDNAME);
		
		//Step 11: type dropdown
		WebElement ty = driver.findElement(By.xpath("//select[@name='accounttype']"));
		Select sel2 = new Select(ty);
		sel2.selectByVisibleText(INDTYPE);
		
		//Step 12: Save
		
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		Thread.sleep(1000);
		
		//Step 13 : Signout
		
		WebElement ele2 = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Thread.sleep(1000);
		Actions act = new Actions(driver);
		
		act.moveToElement(ele2);
		driver.findElement(By.xpath("(//td[@class='small' and @valign='bottom'])[1]")).click();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

	}

}
