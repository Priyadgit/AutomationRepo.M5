package genericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class WebDriverUtility
{

	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	/**
	 * This program will wait for 10 seconds for the web page to be loaded
	 * @param driver
	 */
	public void waitForPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	/**
	 * This program will wait for 10 seconds for the visibility of element
	 * @param driver
	 * @param element
	 */
	public void waitForElementsToBeVisible(WebDriver driver , WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * This program will wait for 10 seconds for the element to be clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementsToClikable(WebDriver driver , WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}	
	/**
	 * This program selects the value of the dropdown by index provided
	 * @param index
	 * @param element
	 */ 	
	public void handleDropdown(int index , WebElement element)
	{
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	/**
	 * This program will select the value of dropdown by visible text
	 * @param element
	 * @param text
	 */
	public void handleDropdown(WebElement element , String text)
	{
		Select sel1 = new Select(element);
		sel1.selectByVisibleText(text);
	}
	/**
	 * This program will select the value of dropdown by String value
	 * @param element
	 * @param value
	 */
	public void handleDropdown(String value, WebElement element )
	{
		Select sel2 = new Select(element);
		sel2.selectByValue(value);
	}
	/**
	 * this program performs move to element on actions 
	 * @param element
	 * @param driver
	 */
	
	public void mouseHoverAction(WebElement element , WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).click().perform();
	}
	/**
	 * this method will perform double click action on a  web element
	 * @param driver
	 * @param element
	 */
	
	public void doubleClick(WebDriver driver,WebElement element )
	{
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}
	/**
	 * this method will perform right click action on a  web element
	 * @param driver
	 * @param element
	 */
	
	public void ContextClick(WebDriver driver,WebElement element )
	{
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}
	/**
	 * This method will drag and drop the src element onto target element
	 * @param srce
	 * @param tare
	 * @param driver
	 */
	public void dragAndDrop(WebElement srce , WebElement tare , WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.dragAndDrop(srce, tare).perform();
	}
	/**
	 * this method will click and hold on a particular web element
	 * @param element
	 * @param driver
	 */
	
	public void clickAndHold(WebElement element , WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.clickAndHold(element).perform();
	}
	
	//Scroll Actions with java script executor
	/**
	 * This method performs the scroll up action in javascript executor
	 * @param driver
	 */
	public void scrollUpAction(WebDriver driver)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("windows.scrollBy(0,350)", "");
		
	}
	/**
	 * This method performs the scroll down action in javascript executor
	 * @param driver
	 */
	
	public void scrollDownAction(WebDriver driver)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("windows.scrollBy(0,-350)", "");
	}
	/**
	 * This method performs the scroll right action in javascript executor by 350 units
	 * @param driver
	 */
	public void scrollRightAction(WebDriver driver)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("windows.scrollBy(350 , 0)", "");
	}
	/**
	 * This method performs the scroll left action in javascript executor by 350 units
	 * @param driver
	 */
	public void scrollLeftAction(WebDriver driver)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("windows.scrollBy(-350 , 0)", "");
	}
	
	//Alert popups
	/**
	 * This method accepts the alert popup by clicking on OK
	 * @param driver
	 */
	public void alertPopupAccept ( WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method accepts the alert popup by clicking on Cancel
	 * @param driver
	 */
	public void alertPopupDismiss ( WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	/**
	 * This method captures the text in alert popup
	 * @param driver
	 * @return
	 */
	
	public String getAlertText(WebDriver driver)
	{
		String text = driver.switchTo().alert().getText();
		return text;
	}
	
	//iframes
	
	/**
	 * This method switches between frames based on name or id
	 * @param driver
	 * @param nameOrid
	 */
	public void SwitchToFrame(WebDriver driver, String nameOrid)
	{
		driver.switchTo().frame(nameOrid);

	}
	/**
	 * This method switches between frames based on web element
	 * @param driver
	 * @param element
	 */
	public void SwitchToFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);

	}
	/**
	 * This method will switch the windows based on partial window title
	 * @param driver
	 * @param partWindowTitle
	 */
	
	public void switchToWindow(WebDriver driver, String partWindowTitle)
	{
		//Step 1 : capture all the window ids
		Set<String> allWindowIds = driver.getWindowHandles();
		
		//Step 2 : Navigate through each window id
		for(String WindowId:allWindowIds)
		{
			//Step 3 : Switch to each window and capture the title
			String acttitle = driver.switchTo().window(WindowId).getTitle();
			
			//step 4 : compare the actual title with expected partial window title
			if(acttitle.contains(partWindowTitle))
			{
				break;
			}
		}
		
	}
	
	public String captureScreenshot(WebDriver driver, String screenShotName) throws IOException
	{
		TakesScreenshot ts =  (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File(".\\ScreenShots\\"+screenShotName+".png");
		
		Files.copy(src, dst);//crct
		
		return dst.getAbsolutePath(); //complete path of screenshot - extent reports
	}
}