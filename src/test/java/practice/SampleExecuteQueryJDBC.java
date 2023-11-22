package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class SampleExecuteQueryJDBC {

	public static void main(String[] args) throws SQLException 
	{
		//Step 1 : Register the driver
		
		Driver driver = new Driver();
		
		DriverManager.registerDriver(driver);
		//Step 2 : Get connection with Database
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empinfo", "root", "root");
		//Step 3 : issue create statement
		Statement state = con.createStatement();
		//Step 4 : execute a query
		ResultSet result = state.executeQuery("select*from empinfo;");
		
		while(result.next())
		{
			String value = result.getString(1)+" "+result.getString(2)+ " "+result.getInt(3);
			System.out.println(value);
		}
		
		
		//Step 5 : Close the Database
		con.close();

	}

}
