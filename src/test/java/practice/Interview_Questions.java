package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Interview_Questions {

	public static void main(String[] args) throws InterruptedException 
	{
		// https://www.countries-ofthe-world.com/capitals-of-the-world.html 
		//highlight only capital of given country
		
		WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.countries-ofthe-world.com/capitals-of-the-world.html ");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//td[text()='Afghanistan']/following-sibling::td"));
		Thread.sleep(1000);

	}

}
