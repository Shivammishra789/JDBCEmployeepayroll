package com.jdbc;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class EmployeePayrollServiceTest {

	@Test
    public void EmployeePayrollDataRetrievedDFromDB_MatchEmployeeCount() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
        Assert.assertEquals(3, employeePayrollData.size());
    }
}
