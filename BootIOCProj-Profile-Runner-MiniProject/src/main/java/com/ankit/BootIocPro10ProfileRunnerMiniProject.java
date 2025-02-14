package com.ankit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.ankit.controller.EmployeeMgmtController;

@SpringBootApplication
public class BootIocPro10ProfileRunnerMiniProject {

	public static void main(String[] args) {
		//get access IOC container
		ApplicationContext context = SpringApplication.run(BootIocPro10ProfileRunnerMiniProject.class, args);
		//get access target spring bean class object
		EmployeeMgmtController empController = context.getBean("empCont", EmployeeMgmtController.class);
				//close the container
		((ConfigurableApplicationContext) context).close();
	}
}
