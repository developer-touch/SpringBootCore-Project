package com.ankit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ankit.model.Employee;
import com.ankit.service.EmployeeMgmtServiceImp;

@Component("empCont")
public class EmployeeController {

	public EmployeeController() {
		System.out.println("EmployeeController::0-arg constructor");
	}
	
	//use EmployeeMgmtService class
	@Autowired
	private EmployeeMgmtServiceImp empService;
	
	//view employee details
	public List<Employee> showEmployeesDetailsByDesg(String desg1,String desg2,String desg3) throws Exception{
		System.out.println("EmployeeController.showEmployeesDetailsByDesg()");
		List<Employee> list = empService.fectEmployeeByDesg(desg1, desg2, desg3);
		return list;
	}
	
	//insert employee details
	public int registerEmployee(Employee employee) throws Exception{
		System.out.println("EmployeeController.registerEmployee()");
		int result = empService.addEmployee(employee);
		return result;
	}
}
