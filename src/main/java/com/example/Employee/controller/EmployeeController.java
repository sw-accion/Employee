package com.example.Employee.controller;

import com.example.Employee.entity.Employee;
import com.example.Employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/employees")
public class EmployeeController {

	@Autowired
	public EmployeeService employeeService;

	public EmployeeController (EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping
	public Employee persistEmployeeDetails (@RequestBody Employee employeeDetails) {
		return employeeService.persistEmployeeDetails (employeeDetails);
	}

	@GetMapping("/{employeeId}/tax-deductions")
	public Employee getEmployeeDetailsById (@PathVariable("employeeId") String employeeId) {
		Employee employee = employeeService.getEmployeeDetailsById (employeeId);
		return employee;
	}
}
