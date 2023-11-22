package practice;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenarion5 {

	public static void main(String[] args) throws InterruptedException 
	{
		WebDriverManager.edgedriver().setup();
		WebDriver driver = new EdgeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin",Keys.TAB,"admin", Keys.ENTER);
		
		driver.findElement(By.xpath("//a[.='Contacts']")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys("Lastname1");
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
		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys("ORG1");
		driver.findElement(By.xpath("//input[@name='search']")).click();
		driver.findElement(By.xpath("//a[text()='ORG1']")).click();
		driver.switchTo().window(mainid);
		
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		
		String text = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if (text.contains("Lastname1"))
		{
			System.out.println(text);
			System.out.println("PASS");
		}
		else
			System.out.println("FAIL");
		
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(ele);
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//td[@class='small' and @valign='bottom'])[1]")).click();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		System.out.println("Sign out Successful");
	}

	}

