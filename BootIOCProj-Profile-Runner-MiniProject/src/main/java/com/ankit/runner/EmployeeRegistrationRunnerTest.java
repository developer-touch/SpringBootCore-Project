package com.ankit.runner;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ankit.controller.EmployeeMgmtController;
import com.ankit.model.Employee;

@Order(2)
@Component("errt")
public class EmployeeRegistrationRunnerTest implements CommandLineRunner {

	public EmployeeRegistrationRunnerTest() {
		System.out.println("EmployeeRegistrationRunnerTest::0-arg constructor");
	}

	@Autowired
	private EmployeeMgmtController empController;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("EmployeeRegistrationRunnerTest.run()");
		// create Scanner class object
		Scanner scn = new Scanner(System.in);

		// Insert Operation
		try {
			System.out.println("************** Select Operation **************");
			System.out.println("Enter the eno :: ");
			int eno = scn.nextInt();
			System.out.println("Enter the ename :: ");
			String ename = scn.next();
			System.out.println("Enter the desg :: ");
			String desg = scn.next();
			System.err.println("Enter the salary :: ");
			double salary = scn.nextDouble();
			System.out.println("Enter the dno :: ");
			int dno = scn.nextInt();
			// create Employee object
			Employee emp = new Employee();
			emp.setEno(eno);
			emp.setEname(ename);
			emp.setDesg(desg);
			emp.setSalary(salary);
			emp.setDept(dno);
			// invoke the method
			int result = empController.addEmployee(emp);
			System.out.println("No.of records inserted.." + result);
		} catch (Exception e) {
			System.err.println("Internal problem try again..." + e.getMessage());
		}

	}

}
