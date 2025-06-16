package webdriverutility;

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
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebDriverUtility {

	public void waitforpagetoload(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public void waitforwebelemnttoload(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// method overloading concept in switch between frames (same method name with
	// diff arguments)
	public void switchtoFrame(WebDriver driver, int index) // using index value
	{
		driver.switchTo().frame(index);
	}

	public void switchtoFrame(WebDriver driver, String nameorID) // using the text
	{
		driver.switchTo().frame(nameorID);
	}

	public void switchtoFrame(WebDriver driver, WebElement frameElement) // using the web element of frame
	{
		driver.switchTo().frame(frameElement);
	}

	// alert pop-ups
	public void switchtoAlertandAccept(WebDriver driver)// to accept the popup (confirmation msg)
	{
		driver.switchTo().alert().accept();
	}

	public void switchtoAlertandDismiss(WebDriver driver) // to dismiss the popup (confirmation msg)
	{
		driver.switchTo().alert().dismiss();
	}

	public String switchtoAlertandgetText(WebDriver driver) // to get the text from popup
	{
		String text = driver.switchTo().alert().getText();
		return text;
	}

	public void switchtoAlertandSendKeys(WebDriver driver, String text) // if we need to send any keys to popups
	{
		driver.switchTo().alert().sendKeys(text);
	}

	// handling drop down
	public void select(WebElement element, int index) // used to select drop down by index
	{
		Select obj = new Select(element); // drop down are developed suing select tag, hence we should always use select while handling it
		obj.selectByIndex(index);
	}

	public void select(WebElement element, String value) // used to select drop down by value
	{
		Select obj = new Select(element);
		obj.selectByValue(value);
	}

	public void select(String text, WebElement element) // used to select drop down by text. Also we changed the order of arg to avaoid the duplicate method
	{
		Select obj = new Select(element);
		obj.selectByVisibleText(text);
	}

	public void mouseHoverOnWebElement(WebDriver driver, WebElement element) 
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	public void clickOnWebElement(WebDriver driver, WebElement element) 
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
	}

	public void doubleClickOnWebElement(WebDriver driver, WebElement element)
	{
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	public void rightClick(WebDriver driver, WebElement element) 
	{
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}

	public void enterInput(WebDriver driver, WebElement element, String text)
	{
		Actions action = new Actions(driver);
		action.click(element).sendKeys(text).perform();
	}

	public void switchToWindowOnTitle(WebDriver driver, String expectedTitle) 
	{
		Set<String> set = driver.getWindowHandles();
		for (String id : set) 
		{
			driver.switchTo().window(id);
			if (driver.getTitle().contains(expectedTitle))
				break;
		}
	}

	public void switchToWindowOnCurrentURL(WebDriver driver, String expectedURL) 
	{
		Set<String> set = driver.getWindowHandles();
		for (String id : set) 
		{
			driver.switchTo().window(id);
			if (driver.getCurrentUrl().contains(expectedURL))
				break;
		}
	}

	public void takeScreenshot(WebDriver driver, String fileName) throws IOException 
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshots/" + fileName + ".png");
		FileHandler.copy(src, dest);
		
	}

	public void scrollByAmount(WebDriver driver, int x, int y) 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + x + "," + y + ")");
	}

}
