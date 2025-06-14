package data_driven_testing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadDatafromJsonFile {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		// to read the data from json file
		//in note pad, the keys and values should be covered separately with double quotes
		//both should be separated with colon, and at the end with coma
		
		JSONParser parser=new JSONParser();
		Object javaobj = parser.parse(new FileReader("D:\\QSpider\\Advance Selenium\\Json File\\Login.json"));
		JSONObject obj = (JSONObject)javaobj; //type casting to get the JSONobject from object
		String value = obj.get("Browser").toString(); //to get the value in string format using the to string
		System.out.println(value);

	}

}
