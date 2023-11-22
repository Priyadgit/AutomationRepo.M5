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

public class Scenario3WithDDT {

	public static void main(String[] args) throws IOException, InterruptedException 
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
				String ORGNAME = wb.getSheet("Organisations").getRow(4).getCell(2).getStringCellValue();
				String INDNAME = wb.getSheet("Organisations").getRow(4).getCell(3).getStringCellValue();
				
				
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
				
				//Step 6: Create Organization with Industry dropdown
				
				driver.findElement(By.xpath("//td[.='Organizations']")).click();
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(ORGNAME);
				WebElement ele = driver.findElement(By.xpath("//select[@name='industry']"));
				ele.click();
				Select sel = new Select(ele);
				sel.selectByVisibleText(INDNAME);
				
				//Step 7 : Save
				
				driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
				Thread.sleep(1000);
				
				//Step 8 : Signout
				
				WebElement ele2 = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				Thread.sleep(1000);
				Actions act = new Actions(driver);
				
				act.moveToElement(ele2);
				driver.findElement(By.xpath("(//td[@class='small' and @valign='bottom'])[1]")).click();
				driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	}

}
