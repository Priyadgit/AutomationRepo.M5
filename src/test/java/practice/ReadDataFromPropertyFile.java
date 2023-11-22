package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadDataFromPropertyFile {

	public static void main(String[] args) throws IOException 
	{
		//Step 1 : open the document in java readable format
		
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		
		//Step 2 : create an object of Properties class from java.util 
		
		Properties prop = new Properties();
		
		//step 3 : load the input stream from properties file
		
		prop.load(fis);
		
		//step 4 : provide the key to read the values
		
		String value = prop.getProperty("browser");
		System.out.println(value);
		
		String value1 = prop.getProperty("url");
		System.out.println(value1);

	}

}
