//IEmployeeMgmtService.java
package com.ankit.service;

import java.util.List;

import com.ankit.model.Employee;

public interface IEmployeeMgmtService {
	public List<Employee> getEmployee(String desg1,String desg2) throws Exception;
	public int addEmployee(Employee employeee) throws Exception;
}
