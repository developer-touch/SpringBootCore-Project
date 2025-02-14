package com.ankit.model;

import lombok.Data;

@Data
public class Employee {
	private Integer employeeNumber;
	private String employeeName;
	private String dest;
	private Double salary;
	private Integer deptNo;
	
	private Double grossSalary;
	private Double netSalary;
}
