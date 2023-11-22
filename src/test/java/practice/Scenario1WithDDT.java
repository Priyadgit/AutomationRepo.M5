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

import objectRepository.LoginPage;

public class Scenario1WithDDT {

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		//Step 1 : Read data from property file
		
		FileInputStream fisp = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p = new Properties();
		p.load(fisp);
		
		String BROWSER = p.getProperty("browser");
		String URL = p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
		
		//Step 2 : Read from excel file
		
		FileInputStream fise = new FileInputStream(".\\src\\test\\resources\\Testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fise);
		String LASTNAME = wb.getSheet("Contacts").getRow(1).getCell(2).getStringCellValue();
		
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
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		driver.manage().window().maximize();
		
		//step 5 : Enter credentials from properties file
		
		//WebElement input = driver.findElement(By.xpath("//input[@name='user_name']"));
		//input.sendKeys(USERNAME , Keys.TAB , PASSWORD,Keys.TAB,Keys.ENTER);
		
		LoginPage lp = new LoginPage(driver);
		//lp.getUserNameEdt().sendKeys(USERNAME);
		//lp.getPasswordEdt().sendKeys(PASSWORD);
		//lp.getLoginBtn().click();
		
		//POM generic library - code optimization
		
		lp.LoginToApp(USERNAME,PASSWORD);
		
		//Step 6 : Create Contact
		
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		Thread.sleep(2000);
		
		//Step 7 : Provide lastname
		
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(LASTNAME);
		
		//Step 8 : Save
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		
		//Step 9 : Validation
		String text = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(text.contains(LASTNAME))
		{
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FAIL");
		}
		
		//Step 10 : Signout
		
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(ele);
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//td[@class='small' and @valign='bottom'])[1]")).click();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		System.out.println("Sign out Successful");	
		
			
	}
}
