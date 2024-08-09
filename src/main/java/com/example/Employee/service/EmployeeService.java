package com.example.Employee.service;

import com.example.Employee.controller.exception.ValidationException;
import com.example.Employee.entity.Employee;
import com.example.Employee.repo.IEmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class EmployeeService {

	@Autowired
	public IEmployeeRepo employeeRepo;

	public Employee persistEmployeeDetails (Employee employee) {
		if (employee != null) {
			if (StringUtils.isEmpty (employee.getDoj ())) {
				throw new ValidationException ("DOJ Not Found");
			}
			if (StringUtils.isEmpty (employee.getEmail ())) {
				throw new ValidationException ("Email Not Found");
			}
			if (StringUtils.isEmpty (employee.getSalary ())) {
				throw new ValidationException ("Sal Not Found");
			}
			if (StringUtils.isEmpty (employee.getPhoneNumber ())) {
				throw new ValidationException ("PhoneNumber Not Found");
			}
			if (StringUtils.isEmpty (employee.getFirstName ())) {
				throw new ValidationException ("FirstName Not Found");
			}
			if (StringUtils.isEmpty (employee.getLastName ())) {
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


	public Employee getEmployeeDetailsById (String empId) {
		Employee employee1 = null;
		Optional<Employee> employee = employeeRepo.findById (Long.valueOf (empId));
		if (employee.isPresent ()) {
			employee1 = employee.get ();
			Double taxAmount = this.calTaxAmount (employee.get ().getSalary ());
			Double cessAmount = this.calCessAmount (employee.get ().getSalary ());
			employee1.setCessAmount (cessAmount);
			employee1.setTaxAmount (taxAmount);
		}
		return employee1;
	}

	private double calTaxAmount (double salary) {
		double tax = 0;

		if (salary > 250000) {
			tax += (Math.min (salary, 500000) - 250000) * 0.05;
		}
		if (salary > 500000) {
			tax += (Math.min (salary, 1000000) - 500000) * 0.10;
		}
		if (salary > 1000000) {
			tax += (salary - 1000000) * 0.20;
		}

		return tax;
	}

	private double calCessAmount (double salary) {
		if (salary > 2500000) {
			return (salary - 2500000) * 0.02;
		}
		return 0;
	}

}
