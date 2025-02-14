//Employee.java
package com.ankit.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component("emp")
public class Employee {
	private Integer eno;
	private String ename;
	private String desg;
	private Double salary;
	private Integer dept;
}
