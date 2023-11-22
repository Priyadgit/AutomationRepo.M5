package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Scenario2 {

	public static void main(String[] args) throws Throwable 
	{
		WebDriver driver = new EdgeDriver();
		driver.get("http://localhost:8888/index.php?action=Login&module=Users");
		WebElement input = driver.findElement(By.xpath("//input[@name='user_name']"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		input.sendKeys("admin" , Keys.TAB , "admin",Keys.TAB,Keys.ENTER);
		driver.findElement(By.xpath("//td[.='Organizations']")).click();
		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys("Org3");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		Thread.sleep(2000);
		WebElement ele = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		String ele2=ele.getText();
		if(ele2.contains("org2 -  Organization Information"))
		{
			System.out.println("PASS");
		}
		else
		{
			System.out.println("FAIL");
		}
	
		Thread.sleep(2000);  
	driver.findElement(By.xpath("(//td[@class='small' and @valign='bottom'])[1]")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//a[.='Sign Out']")).click();
	}

}

