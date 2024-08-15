package com.example.Employee.service;

import com.example.Employee.entity.Employee;
import com.example.Employee.repo.IEmployeeRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = EmployeeServiceTest.class)
public class EmployeeServiceTest {

	@Spy
	@InjectMocks
	EmployeeService employeeService;

	@Mock
	private IEmployeeRepo employeeRepo;


	@Test
	public void testCreateEmployee () {
		Employee employeeObj = this.testData ();
		BDDMockito.given (this.employeeRepo.save (Mockito.any ())).willReturn (employeeObj);
		Employee cretedEmployee = employeeService.persistEmployeeDetails (employeeObj);
		org.junit.Assert.assertThat (employeeObj, org.hamcrest.Matchers.is (cretedEmployee));
	}

	@Test
	public void getEmployeeDetailsById () {

		Optional<Employee> employee = Optional.ofNullable (this.testData ());
		BDDMockito.given (this.employeeRepo.findById (Mockito.anyLong ())).willReturn (employee);
		Employee
			employee1 = this.employeeService.getEmployeeDetailsById (employee.get ().getEmployeeId ());
		Assertions.assertEquals (employee1.getFirstName (), employee.get ().getFirstName ());
	}


	private Employee testData(){
		Employee employeeObj = Employee.builder ()
			.employeeId ("1")
			.doj ("20-05-2020")
			.email ("swami.test@gmail.com")
			.lastName ("Test")
			.salary (200000.00)
			.firstName ("Testing")
			.phoneNumber ("9989365035")
			.build ();
		return employeeObj;
	}


}
