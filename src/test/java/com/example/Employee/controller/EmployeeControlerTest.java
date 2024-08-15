package com.example.Employee.controller;

import com.example.Employee.EmployeeApplication;
import com.example.Employee.entity.Employee;
import com.example.Employee.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes= EmployeeApplication.class)
@WebMvcTest(EmployeeController.class)
@AutoConfigureWebClient
public class EmployeeControlerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService  employeeService;


	@Test
	public void testCreateEmployee () throws Exception {
		Employee employeeObj = this.testData ();
		Mockito.when (this.employeeService.persistEmployeeDetails (ArgumentMatchers.any()))
			.thenReturn (employeeObj);
		ObjectMapper mapper = new ObjectMapper ();
		String expectedJson = mapper.writeValueAsString (employeeObj);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
			.post ("/v1/api/employees")
			.accept (MediaType.APPLICATION_JSON).content (expectedJson).headers (null)
			.contentType (MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform (requestBuilder).andReturn ();
		MockHttpServletResponse response = result.getResponse ();
		Mockito.verify (this.employeeService, Mockito.times (1))
			.persistEmployeeDetails (ArgumentMatchers.any());
		Assertions.assertThat (response.getStatus ()).isEqualTo (HttpStatus.OK.value ());
	}

	@Test
	public void testGetEmployyeDetails () throws Exception {
		Employee employeeObj = this.testData ();

		Mockito.when (this.employeeService.getEmployeeDetailsById (ArgumentMatchers.any()))
			.thenReturn (employeeObj);
		ObjectMapper mapper = new ObjectMapper ();
		String expectedJson = mapper.writeValueAsString (employeeObj);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
			.get ("/v1/api/employees/{employeeId}/tax-deductions")
			.accept (MediaType.APPLICATION_JSON).content (expectedJson).headers (null)
			.contentType (MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform (requestBuilder).andReturn ();
		MockHttpServletResponse response = result.getResponse ();
		Mockito.verify (this.employeeService, Mockito.times (1)).getEmployeeDetailsById (ArgumentMatchers.any());
		Assertions.assertThat (response.getStatus ()).isEqualTo (HttpStatus.OK.value ());

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
