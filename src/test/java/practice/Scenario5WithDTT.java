package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

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

public class Scenario5WithDTT {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException 
	{
				//Step 1 : Read from Properties File
		
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
				String ORGNAME = wb.getSheet("Contacts").getRow(4).getCell(3).getStringCellValue();
				String INDNAME = wb.getSheet("Organisations").getRow(7).getCell(3).getStringCellValue();
				String INDTYPE = wb.getSheet("Organisations").getRow(7).getCell(4).getStringCellValue();
				
				
				
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
				
				WebElement input = driver.findElement(By.xpath("//input[@name='user_name']"));
				input.sendKeys(USERNAME , Keys.TAB , PASSWORD,Keys.TAB,Keys.ENTER);
				
				//Step 6: Create Contact with Organisation
				
				driver.findElement(By.xpath("//a[.='Contacts']")).click();
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
				driver.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();
				
				String mainid = driver.getWindowHandle();
				System.out.println(mainid);
				Set<String> allids = driver.getWindowHandles();
				System.out.println(allids);
				
				for ( String s : allids)
				{
					if (!mainid.equals(s))
						driver.switchTo().window(s);
					
				}
				driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(ORGNAME);
				driver.findElement(By.xpath("//input[@name='search']")).click();
				driver.findElement(By.xpath("//a[@id='1']")).click();
				driver.switchTo().window(mainid);
				
				//Step 7 : Save
				
				driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
				
				//Step 8 : Validate
				
				String text = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				
				if (text.contains(LASTNAME))
				{
					System.out.println(text);
					System.out.println("PASS");
				}
				else
					System.out.println("FAIL");
				
				//Step 9 : Signout
				
				WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				Actions act = new Actions(driver);
				act.moveToElement(ele);
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//td[@class='small' and @valign='bottom'])[1]")).click();
				driver.findElement(By.xpath("//a[.='Sign Out']")).click();
				System.out.println("Sign out Successful");

	}

}
