package com.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class DBConnection {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
		String uname = "root";
		String pwd ="root";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver is loaded...");		
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		listDrivers();
		
		Connection connection;
		try {
			connection = DriverManager.getConnection(url, uname, pwd);
			System.out.println("Connection to the DB succsessful..! " + connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void listDrivers() {
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while(drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			System.out.println("Drive Name:" + driver);
		}
		
	}
}
