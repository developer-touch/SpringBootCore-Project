package com.ankit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ankit.model.Employee;

@Profile({"mysql"})
@Repository("emp-mysql")
public class MySQLEmployeeDaoImp implements IEmployeeDao{

	private static final String GET_EMP_BY_DESG = """
			SELECT 
			eno,ename,job,salary,dno 
			FROM employee
			WHERE job in (?,?)
			""";
	
	private static final String INSERT_EMP = """
			INSERT INTO
			employee(eno,ename,job,salary,dno)
			VALUES(?,?,?,?,?)
			""";
	
	//inject the data source object to get pooled connection 
	@Autowired
	private DataSource ds;
	
	public MySQLEmployeeDaoImp() {
		System.out.println("MySQLEmployeeDaoImp::0-arg constructor");
	}
	
	@Override
	public List<Employee> fetchByDesg(String desg1, String desg2) throws Exception {
		System.out.println("MySQLEmployeeDaoImp.fetchByDesg()"+ds.getClass());
		//create ArrayList object having list of employee
		ArrayList<Employee> empList = null;
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_EMP_BY_DESG);){
			//set value to QP
			ps.setString(1, desg1);
			ps.setString(2, desg2);
			//execute the query
			try(ResultSet rs = ps.executeQuery()){
				//assigning object to ArrayList
				empList = new ArrayList<Employee>();
				//process the result
				while(rs.next()) {
					//create Employee object to copy RS object to employee object
					Employee emp = new Employee();
					emp.setEno(rs.getInt(1));
					emp.setEname(rs.getString(2));
					emp.setDesg(rs.getString(3));
					emp.setSalary(rs.getDouble(4));
					emp.setDept(rs.getInt(5));
					//add to empList object
					empList.add(emp);
				}
			}
		}
		catch (SQLException se) {
			throw se;
		}
		catch (Exception e) {
			throw e;
		}
		
		return empList;
	}

	@Override
	public int insert(Employee employee) throws Exception {
		System.out.println("MySQLEmployeeDaoImp.insert()");
		int result = 0;
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(INSERT_EMP)){
			//set value to QP
			ps.setInt(1, employee.getEno());
			ps.setString(2, employee.getEname());
			ps.setString(3, employee.getDesg());
			ps.setDouble(4, employee.getSalary());
			ps.setInt(5, employee.getDept());
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
