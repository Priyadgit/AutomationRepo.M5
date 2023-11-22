package practice;

import java.io.IOException;

import org.openqa.selenium.By;
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

public class Scenario3WithDDTandGU {

	public static void main(String[] args) throws IOException, InterruptedException 
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
		 String ORGNAME = eUtil.ReadFromExcelFile("Organisations", 4, 2)+jUtil.getRandomNumber();
		 String INDNAME = eUtil.ReadFromExcelFile("Organisations", 4, 3);
		 
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
		
		//Step 6: Create Organization with Industry dropdown
		
		driver.findElement(By.xpath("//td[.='Organizations']")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(ORGNAME);
		
		//Step 7 : Handle industry dropdown
		WebElement ele = driver.findElement(By.xpath("//select[@name='industry']"));
		ele.click();
		wUtil.handleDropdown(ele, INDNAME);
		
		
		//Step 7 : Save
		
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		Thread.sleep(1000);
		
		//Step 8 : Signout
		
		WebElement ele2 = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseHoverAction(ele, driver);
		Thread.sleep(1000);
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("Logout is successfull");

	}

}
