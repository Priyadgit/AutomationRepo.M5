package genericUtilities;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

public class JavaUtility 
{
	/**
	 * This method will return the current system date in specified format
	 * @author Priyadarshini.H.M
	 * @return
	 */

	public String GetSystemDateFormat()
	{
		Date d = new Date(getRandomNumber());
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		String currentdate = formatter.format(d);
		return currentdate;
	}
	
	public int getRandomNumber()
	{
		Random ran = new Random();
		int rand = ran.nextInt(1000);
		return rand;
		
	}

}
