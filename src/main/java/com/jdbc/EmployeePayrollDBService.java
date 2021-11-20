package com.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.mysql.jdbc.Statement;

public class EmployeePayrollDBService {
	
	 private Connection getConnection() throws SQLException {
	        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
	        String userName = "root";
	        String password = "root";
	        Connection connection;
	        System.out.println("Connecting to database: "+jdbcURL);
	        connection = DriverManager.getConnection(jdbcURL, userName, password);
	        System.out.println("Connection is successful! " +connection);
	        return connection;
	    }
	 
	 private List<EmployeePayrollData> getEmployeePayrollDataUsingDB(String sql) {
	        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
	        try (Connection connection = this.getConnection()) {
	            java.sql.Statement statement = connection.createStatement();
	            ResultSet result = statement.executeQuery(sql);
	            while (result.next()) {
	                int id = result.getInt("id");
	                String name = result.getString("name");
	                double salary = result.getDouble("salary");
	                LocalDate startDate = result.getDate("start").toLocalDate();
	                employeePayrollList.add(new EmployeePayrollData(id, name, salary, startDate));
	            }
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	        return employeePayrollList;
	    }
	 

	 public List<EmployeePayrollData> readData() {
	        String sql = "SELECT * FROM employee_payroll";
	        return getEmployeePayrollDataUsingDB(sql);
	    }
	 
	
	
}
