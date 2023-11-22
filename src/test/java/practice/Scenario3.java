package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Scenario3 {

	public static void main(String[] args) throws InterruptedException 
	{
WebDriver driver = new EdgeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin",Keys.TAB,"admin", Keys.ENTER);
		driver.findElement(By.xpath("//td[.='Organizations']")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys("INFY");
		WebElement ele = driver.findElement(By.xpath("//select[@name='industry']"));
		ele.click();
		Select sel = new Select(ele);
		sel.selectByVisibleText("Chemicals");
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		Thread.sleep(1000);
		WebElement ele2 = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Thread.sleep(1000);
		Actions act = new Actions(driver);
		
		act.moveToElement(ele2);
		driver.findElement(By.xpath("(//td[@class='small' and @valign='bottom'])[1]")).click();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
	}

}
