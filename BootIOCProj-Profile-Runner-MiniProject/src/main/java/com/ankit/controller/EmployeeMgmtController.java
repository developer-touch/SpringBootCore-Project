//EmployeeMgmtController.java
package com.ankit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ankit.model.Employee;
import com.ankit.service.IEmployeeMgmtService;

@Controller("empCont")
public class EmployeeMgmtController {
	
	@Autowired
	private IEmployeeMgmtService empService;
	
	public EmployeeMgmtController() {
		System.out.println("EmployeeMgmtController::0-arg constructor");
	}
	
	public List<Employee> fetchEmployeeByDesg(String desg1, String desg2) throws Exception {
		System.out.println("EmployeeMgmtController.fetchEmployeeByDesg()");
		List<Employee> empList = empService.getEmployee(desg1, desg2);
		if(empList!=null)
			System.out.println("Employee records are found in the list and employee records are.....");
		else
			System.out.println("Employee records are not found in the list.....");
		return empList;
	}
	public int addEmployee(Employee employeee) throws Exception {
		System.out.println("EmployeeMgmtController.addEmployee()");
		//invoke the method
		int result = empService.addEmployee(employeee);
		//return the object
		return result;
	}
}
