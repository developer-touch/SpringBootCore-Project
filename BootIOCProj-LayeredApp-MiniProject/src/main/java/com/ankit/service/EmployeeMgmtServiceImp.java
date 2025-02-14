package com.ankit.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ankit.dao.EmployeeDAOImpl;
import com.ankit.model.Employee;

@Component("empService")
public class EmployeeMgmtServiceImp implements IEmployeeMgmtService{

	public EmployeeMgmtServiceImp() {
		System.out.println("EmployeeMgmtServiceImp::0-arg constructor");
	}
	
	//injecting EmployeeDAO
	@Autowired
	private EmployeeDAOImpl empDao;
	
	// view employee details
	@Override
	public List<Employee> fectEmployeeByDesg(String desg1, String desg2, String desg3) throws Exception {
		System.out.println("EmployeeMgmtServiceImp.fectEmployeeByDesg()");
		//change desg case to Upper case
		desg1 = desg1.toUpperCase();
		desg2 = desg2.toUpperCase();
		desg3 = desg3.toUpperCase();
		//use EmpDao
		List<Employee> employeeList = empDao.fetchEmployeeByDesg(desg1, desg2, desg3);
		//calculate gross salary and net salary for the employee
		employeeList.forEach(emp->{
			emp.setGrossSalary(emp.getSalary()+(emp.getSalary()*0.4));
			emp.setNetSalary(emp.getSalary()-(emp.getSalary()*0.2));
		});
		//Sort the object by empNames
		employeeList.sort(new Comparator<Employee>() {

			@Override
			public int compare(Employee emp1, Employee emp2) {
				return emp1.getEmployeeName().compareTo(emp2.getEmployeeName());
			}
		});
		return employeeList;
	}

	//insert employee details
	@Override
	public int addEmployee(Employee employee) throws Exception {
		System.out.println("EmployeeMgmtServiceImp.addEmployee()");
		int result = 0;
		if (empDao!=null) {
			result = empDao.insert(employee);
		}
		else {
			throw new IllegalArgumentException("Employee DAO class is not injected");
		}
		return result;
	}
	
}
