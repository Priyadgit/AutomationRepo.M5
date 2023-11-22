package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class WriteDataIntoPropertyFile {

	public static void main(String[] args) throws IOException
	{
		//Step 1 : open the document in java readable format
		
				//FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
				
				//Step 2 : create an object of Properties class from java.util 
				
				Properties prop = new Properties();
				prop.setProperty("username", "admin");
				prop.setProperty("password", "admin");
				
				FileOutputStream fos = new FileOutputStream(".\\src\\test\\resources\\WritePropertyFile.properties.txt");
				
				prop.store(fos, "write into file created");
				System.out.println("Property file updated");

	}

}
