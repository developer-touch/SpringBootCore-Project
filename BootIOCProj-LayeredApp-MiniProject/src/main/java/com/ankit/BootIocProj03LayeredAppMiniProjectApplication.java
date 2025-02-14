package com.ankit;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.ankit.controller.EmployeeController;
import com.ankit.model.Employee;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@SpringBootApplication
public class BootIocProj03LayeredAppMiniProjectApplication {
	
	//inject environment to get application.properties files properties
	@Autowired
	private Environment env;
	
	//specified another connection pool 
	/*@Bean(name = "c3P0")
	public ComboPooledDataSource createC3P0DS() throws Exception {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		ds.setDriverClass(env.getProperty("com.mysql.cj.jdbc.Driver"));
		ds.setJdbcUrl(env.getProperty("jdbc:mysql://localhost:3306/ankit"));
		ds.setUser(env.getProperty("root"));
		ds.setPassword("ankit@123");
		return ds;
	}*/
	
	@Bean(name = "c3P0")
	public ComboPooledDataSource createC3P0DS() throws Exception {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		ds.setDriverClass(env.getProperty("spring.datasource.driver-class-name"));
		ds.setJdbcUrl("spring.datasource.url");
		ds.setUser("spring.datasource.username");
		ds.setPassword("spring.datasource.password@123");
		return ds;
	}
	
	public static void main(String[] args) {
		//create IOC container 
		ApplicationContext context = SpringApplication.run(BootIocProj03LayeredAppMiniProjectApplication.class, args);
		//get controller class object reference
		EmployeeController empController = context.getBean("empCont",EmployeeController.class);
		
		//create Scanner class object to read desg-value
		Scanner scn = new Scanner(System.in);
		
		System.out.println("Enter 1st dest::");
		String desg1 = scn.next();
		System.out.println("Enter 2nd dest::");
		String desg2 = scn.next();
		System.out.println("Enter 3rd dest::");
		String desg3 = scn.next();
		try {
			//invoke the b.method
			List<Employee> list = empController.showEmployeesDetailsByDesg(desg1, desg2, desg3);
			list.forEach(emp->{
				System.out.println(emp+" ");
			});
		}
		catch(Exception e) {
			System.out.println("Internal Problem :: try again"+e.getMessage());
		}
		
		//insert employee details
		try {
			//read the employee details from the end user
			System.out.println("Enter the employee number :: ");
			int eno = scn.nextInt();
			System.out.println("Enter the employee name :: ");
			String name = scn.next();
			System.out.println("Enter the employee jon :: ");
			String job = scn.next();
			System.out.println("Enter the employee sarary ::");
			Double salary = scn.nextDouble();
			System.out.println("Enter the dept number :: ");
			int dno = scn.nextInt();
			
			//create employee class object
			Employee emp = new Employee();
			emp.setEmployeeNumber(eno);
			emp.setEmployeeName(name);
			emp.setDest(job);
			emp.setSalary(salary);
			emp.setDeptNo(dno);
			
			//invoke the b.method
			int registerEmp = empController.registerEmployee(emp);
			System.out.println("No.of emp inserted :: "+registerEmp);
		}
		catch (Exception e) {
			System.out.println("Internal problem :: try agian"+e.getMessage());
		}
		//close the container
		((ConfigurableApplicationContext) context).close();
	}

}
