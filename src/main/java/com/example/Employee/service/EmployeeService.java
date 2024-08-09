package com.example.Employee.service;

import com.example.Employee.entity.Employee;
import com.example.Employee.repo.IEmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	@Autowired
	public IEmployeeRepo employeeRepo;

	public Employee persistEmployeeDetails (Employee employee) {
		Employee employeeObj = Employee.builder ()
			.doj (employee.getDoj ())
			.email (employee.getEmail ())
			.lastName (employee.getLastName ())
			.salary (employee.getSalary ())
			.firstName (employee.getFirstName ())
			.phoneNumber (employee.getPhoneNumber ())
			.build ();
		return employeeRepo.save (employeeObj);
	}
}
