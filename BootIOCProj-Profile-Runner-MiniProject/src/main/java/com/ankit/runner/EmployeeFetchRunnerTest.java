package com.ankit.runner;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ankit.controller.EmployeeMgmtController;
import com.ankit.model.Employee;

@Order(1)
@Component("efrt")
public class EmployeeFetchRunnerTest implements CommandLineRunner {

	public EmployeeFetchRunnerTest() {
		System.out.println("EmployeeFetchRunnerTest::0-arg constructor");
	}
	
	@Autowired
	private EmployeeMgmtController empController;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("EmployeeFetchRunnerTest.run()");
		
		//Scanner class object
		Scanner scn = new Scanner(System.in);
		
		// Select operation
		try {
			System.out.println("************** Select Operation **************");
			System.out.println("Enter the first desg:: ");
			String desg1 = scn.next();
			System.out.println("Enter the second desg :: ");
			String desg2 = scn.next();
			// invoke the b.method
			List<Employee> empList = empController.fetchEmployeeByDesg(desg1, desg2);
			empList.forEach(System.out::println);
		} catch (Exception e) {
			System.err.println("Internal problem try again..." + e.getMessage());
		}
	}

}
