//IEmployeeDAO.java
package com.ankit.dao;

import java.util.List;

import com.ankit.model.Employee;

public interface IEmployeeDAO {
	public List<Employee> fetchEmployeeByDesg(String desg1,String desg2,String desg3) throws Exception;
	public int insert(Employee employee) throws Exception;
}
