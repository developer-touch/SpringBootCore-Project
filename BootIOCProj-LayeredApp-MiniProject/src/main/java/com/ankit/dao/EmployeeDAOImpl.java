
package com.ankit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ankit.model.Employee;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Component("empDAO")
public class EmployeeDAOImpl implements IEmployeeDAO {
	
	private static final String GET_EMP_BY_DESG = "select ENO,ENAME,JOB,SALARY,DNO from EMPLOYEE where JOB in(?,?,?)";
	private static final String INSERT_EMP = "insert into EMPLOYEE (ENO,ENAME,JOB,SALARY,DNO) values (?,?,?,?,?)";
	
	public EmployeeDAOImpl() {
		System.out.println("EmployeeDAOImpl::0-arg constructor");
	}
	
	//initializing the arrayList
	ArrayList<Employee> list = null;
	
	//injection data source class object to get pooled connection  
	@Autowired
	private ComboPooledDataSource ds;

	@Override
	public List<Employee> fetchEmployeeByDesg(String desg1, String desg2, String desg3) throws Exception {
		System.out.println("EmployeeDAOImpl.fetchEmployeeByDesg()"+ds);
		//creating arrayList object here
		list = new ArrayList<Employee>();
		try(Connection con = ds.getConnection();
				//create prePared statement object having SQL query
				PreparedStatement ps = con.prepareStatement(GET_EMP_BY_DESG);
				){
			//set value to Query parameter
			ps.setString(1, desg1);
			ps.setString(2, desg2);
			ps.setString(3, desg3);
			//execute the query
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					//copy the each record from the RS object to employee class object
					Employee emp = new Employee();
					emp.setEmployeeNumber(rs.getInt(1));
					emp.setEmployeeName(rs.getString(2));
					emp.setDest(rs.getString(3));
					emp.setSalary(rs.getDouble(4));
					emp.setDeptNo(rs.getInt(5));
					//add to employee list 
					//System.out.println(list);
					list.add(emp);
				}
			}
		}
		catch(SQLException se) {
			throw se;	// Exception re-throwing
		}
		catch(Exception e) {
			throw e;	// Exception re-throwing
		}
		//System.out.println(list);
		return list;
	}

	@Override
	public int insert(Employee employee) throws Exception {
		System.out.println("EmployeeDAOImpl.insert()");
		
		int result = 0;
		
		try(Connection con = ds.getConnection();
				//create PS object having SQL Query
				PreparedStatement ps = con.prepareStatement(INSERT_EMP);){
			//set values to QP
			ps.setInt(1, employee.getEmployeeNumber());
			ps.setString(2, employee.getEmployeeName());
			ps.setString(3, employee.getDest());
			ps.setDouble(4, employee.getSalary());
			ps.setInt(5, employee.getDeptNo());
			//execute the query
			result = ps.executeUpdate();
		}
		catch (SQLException se) {
			throw se;
		}
		catch (Exception e) {
			throw e;
		}
		return result;
	}

}
