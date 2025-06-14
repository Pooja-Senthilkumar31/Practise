package takescreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

public class Screenshot {
	@Test
	public void demo() throws IOException
	{
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
		
		Date d= new Date(); //evertime runing the code, old SS get replace by new SS. hence adding date to it to find the diff
		String NewDate = d.toString().replace(" ", "_").replace(":", "_"); //replacing the date chars which is string to some other chars
		System.out.println(NewDate);
		
		TakesScreenshot ts=(TakesScreenshot)driver; //downcasting here since we dont have takesscreenshot directly from driver interface
		File src = ts.getScreenshotAs(OutputType.FILE); //the taken SS will be auto deleted once the code execution over. Hence creating this temporary location and changing it to permanent location
		File dest = new File("./Screenshots/fb"+NewDate+".png"); //. represents current file directory (current proj) and folder name and file name and file type
		FileHandler.copy(src, dest);
	}

}
