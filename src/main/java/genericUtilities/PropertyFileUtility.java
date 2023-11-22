package genericUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtility {

	
		/**
		 * @author Priyadarshini.H.M
		 * This class consists of re-usable methods related to property file
		 */
		
			/**
			 * 
			 * @param key
			 * @return value
			 * @throws IOException 
			 */
			
			public String ReadDatafromPropertyFile(String key) throws IOException 
			{
				FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
				Properties p = new Properties();
				p.load(fis);
				String value = p.getProperty(key);
				return value;
			}

		

	}


