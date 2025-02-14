//IEmployeeMgmtService.java
package com.ankit.service;

import java.util.List;

import com.ankit.model.Employee;

public interface IEmployeeMgmtService {
	public List<Employee> fectEmployeeByDesg(String desg1,String desg2,String desg3) throws Exception;
	public int addEmployee(Employee employee) throws Exception;
}
