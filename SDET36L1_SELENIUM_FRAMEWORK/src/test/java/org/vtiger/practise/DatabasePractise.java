package org.vtiger.practise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabasePractise {

	public static void main(String[] args) {
		// create the object for driver class which is given database provider
		
			try {
				Driver driver = new Driver();
				//register the driver to jdbc
				DriverManager.registerDriver(driver);
				//establish the connection 
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tyss","root","root");
			//create statement
		Statement statement = connection.createStatement();
		//execute query
	ResultSet result = statement.executeQuery("select * from sdet36;");
	//iterate the data
	int count=0;
	while(result.next()) {
		System.out.println(result.getString(1)+ "    "+ result.getString("empName")+ "  "+ result.getString(4));
	if(result.getString("empName").equals("Dinesh"))
	{
		count++;
		System.out.println("data is present ");
		break;
	}
	}
	if(count==0)
		System.out.println("data is not present");
	
	connection.close();
	
	
	
		
	
		
						
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		

	}

}
