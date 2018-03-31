package com.nikhil.spring.app.controller.admin;

import java.math.BigDecimal;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nikhil.spring.app.model.Employee;
import com.nikhil.spring.app.service.EmployeeService;

@Controller
public class Hello {

	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping("/")
	@ResponseBody
	public String hello() {
		Employee employee1 = new Employee();
        employee1.setName("Han Yenn");
        employee1.setJoiningDate(new LocalDate(2010, 10, 10));
        employee1.setSalary(new BigDecimal(10000));
        employee1.setSsn("ssn001");
		employeeService.saveEmployee(employee1);
		return "Hello World 1";
	}
	
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public String hello(@RequestBody Employee employee) {
		employee.setJoiningDate(new LocalDate(2010, 10, 10));
        employee.setSalary(new BigDecimal(10000));
        employee.setSsn("ssn001");
		employeeService.saveEmployee(employee);
		return employee.getName();
	}
	
}
