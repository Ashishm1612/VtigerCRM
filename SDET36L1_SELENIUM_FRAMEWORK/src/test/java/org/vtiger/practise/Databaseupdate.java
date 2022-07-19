package org.vtiger.practise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class Databaseupdate {

	public static void main(String[] args) {
		try {
			Driver driver = new Driver();

			//register the driver to jdbc
			DriverManager.registerDriver(driver);
			//establish the connection 
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tyss","root","root");
			//create statement
			Statement statement = connection.createStatement();
			//execute query
			int result = statement.executeUpdate("insert into sdet36(empName,empId,phoneNumber) values('Ambrit',1234567890,7894563214);");
			if(result==1)
				System.out.println("data is inserted");
			else
				System.out.println("data is not present");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();


		}	

	}
}
