//IEmployee.java
package com.ankit.dao;

import java.util.List;

import com.ankit.model.Employee;

public interface IEmployeeDao {
	public List<Employee> fetchByDesg(String desg1,String desg2) throws Exception;
	public int insert(Employee employee) throws Exception; 
}
