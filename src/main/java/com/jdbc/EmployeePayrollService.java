package com.jdbc;

import java.time.LocalDate;
import java.util.List;


public class EmployeePayrollService {
	EmployeePayrollDBService employeePayrollDBService = new EmployeePayrollDBService();
	public enum IOService {DB_IO}
	private List<EmployeePayrollData> employeePayrollList;

	public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService)  {
        if(ioService.equals(IOService.DB_IO))
            this.employeePayrollList = employeePayrollDBService.readData();
        return this.employeePayrollList;
    }

    public void updateEmployeeSalary(String name, double salary)  {
        int result = employeePayrollDBService.updateEmployeeData(name, salary);
        if(result == 0)
            return;
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
        if( employeePayrollData != null )
            employeePayrollData.salary = salary;
    }

    public void updateEmployeeSalaryUsingPreparedStatement(String name, double salary)  {
        int result = employeePayrollDBService.updateEmployeeDataPreparedStatement(name, salary);
        if(result == 0)
            return;
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
        if( employeePayrollData != null )
            employeePayrollData.salary = salary;
    }

    public boolean checkEmployeePayrollInSyncWithDB(String name)  {
        List<EmployeePayrollData> employeePayrollDataList = employeePayrollDBService.getEmployeePayrollData(name);
        return employeePayrollDataList.get(0).equals(getEmployeePayrollData(name));
    }

    private EmployeePayrollData getEmployeePayrollData(String name) {
        return this.employeePayrollList.stream()
                              .filter(employeePayrollDataItem -> employeePayrollDataItem.name.equals(name))
                              .findFirst()
                              .orElse(null);
    }

    public List<EmployeePayrollData> readEmployeePayrollForDateRange(IOService ioService, LocalDate startDate, LocalDate endDate)  {
        if( ioService.equals(IOService.DB_IO))
            return employeePayrollDBService.getEmployeeForDateRange(startDate, endDate);
        return null;
    }
}