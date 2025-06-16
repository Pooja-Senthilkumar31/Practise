package FileUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	//to reduce the code lines, we use this method
	//here we creating separate class for all methods and we can call the class wherever required
	//so no need of mail class here, bcz we are not gng to execute anything here, we just using this to call
	
	public String readDataFromPropertiesFile(String key) throws IOException
	{
		//Read data from properties file
		FileInputStream fis=new FileInputStream("D:\\QSpider\\Advance Selenium\\Properties File\\LoginwithNinza.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String value = prop.getProperty(key);
		
		return value;
	}

}
