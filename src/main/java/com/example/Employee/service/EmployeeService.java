package com.example.Employee.service;

import com.example.Employee.controller.exception.ValidationException;
import com.example.Employee.entity.Employee;
import com.example.Employee.repo.IEmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class EmployeeService {

	@Autowired
	public IEmployeeRepo employeeRepo;

	public Employee persistEmployeeDetails (Employee employee) {
		if (employee != null) {
			if(StringUtils.isEmpty (employee.getDoj ())){
				throw new ValidationException ("DOJ Not Found");
			}
			if(StringUtils.isEmpty (employee.getEmail ())){
				throw new ValidationException ("Email Not Found");
			}
			if(StringUtils.isEmpty (employee.getSalary ())){
				throw new ValidationException ("Sal Not Found");
			}
			if(StringUtils.isEmpty (employee.getPhoneNumber ())){
				throw new ValidationException ("PhoneNumber Not Found");
			}
			if(StringUtils.isEmpty (employee.getFirstName ())){
				throw new ValidationException ("FirstName Not Found");
			}
			if(StringUtils.isEmpty (employee.getLastName ())){
				throw new ValidationException ("LastName Not Found");
			}
			Employee employeeObj = Employee.builder ()
				.doj (employee.getDoj ())
				.email (employee.getEmail ())
				.lastName (employee.getLastName ())
				.salary (employee.getSalary ())
				.firstName (employee.getFirstName ())
				.phoneNumber (employee.getPhoneNumber ())
				.build ();
			return employeeRepo.save (employeeObj);
		} else {
			throw new ValidationException ("Data Not Found");
		}
	}
}
