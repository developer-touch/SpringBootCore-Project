package com.ankit.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ankit.dao.IEmployeeDao;
import com.ankit.model.Employee;

@Service("empService")
public class EmployeeMgmtServiceImpl implements IEmployeeMgmtService{

	@Autowired
	private IEmployeeDao empDao;
	
	public EmployeeMgmtServiceImpl() {
		System.out.println("EmployeeMgmtServiceImpl::0-arg constructor");
	}
	
	@Override
	public List<Employee> getEmployee(String desg1, String desg2) throws Exception {
		System.out.println("EmployeeMgmtServiceImpl.getEmployee()");
		//convert the name to Upper case
		desg1 = desg1.toUpperCase();
		desg2 = desg2.toUpperCase();
		//invoke the method
		List<Employee>empList = empDao.fetchByDesg(desg1, desg2);
		//sort the empList
		empList.sort(new Comparator<Employee>() {
			@Override
			public int compare(Employee e1, Employee e2) {
				return e1.getEname().compareTo(e2.getEname());
			}
		});
		//return the employee list
		return empList;
	}

	@Override
	public int addEmployee(Employee employeee) throws Exception {
		System.out.println("EmployeeMgmtServiceImpl.addEmployee()");
		//invoke the method
		int result = empDao.insert(employeee);
		if(result!=0) {
			System.out.println("Employee Registered Successful...");
		}else {
			System.out.println("Employee Not Registered....");
		}
		//return the object
		return result;
	}

}
