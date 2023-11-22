package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class Scenario4 {

	public static void main(String[] args) throws InterruptedException 
	{
		WebDriver driver = new EdgeDriver();
		driver.get("http://localhost:8888/index.php?action=Login&module=Users");
		WebElement input = driver.findElement(By.xpath("//input[@name='user_name']"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		input.sendKeys("admin" , Keys.TAB , "admin",Keys.TAB,Keys.ENTER);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text()='Organizations'][1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys("ORG7");
		Thread.sleep(1000);
		//industry dropdown
		WebElement dd = driver.findElement(By.xpath("//select[@name='industry']"));
		Select sel = new Select(dd);
		sel.selectByVisibleText("Energy");
		//type dropdown
		WebElement ty = driver.findElement(By.xpath("//select[@name='accounttype']"));
		Select sel2 = new Select(ty);
		sel2.selectByVisibleText("Customer");
		//save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		Thread.sleep(1000);
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		//logout
		driver.findElement(By.xpath("(//td[@class='small' and @valign='bottom'])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();

	}

}
