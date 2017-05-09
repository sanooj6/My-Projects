package com.EmployeeTrackingSystem.Utilities;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	public static Connection getConnection()
	{
		java.sql.Connection con=null;
		try
		{
			System.out.println("inside getconnection");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("connected");
			con=DriverManager.getConnection("jdbc:mysql://localhost/ets","root","carinov");
			System.out.println("connected");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
}
