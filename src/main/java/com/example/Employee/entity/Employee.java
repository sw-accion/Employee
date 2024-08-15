package com.example.Employee.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Entity
@Table(name = "poc_student_test")
@Builder
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private String employeeId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "doj")
	private String doj;

	@Column(name = "email")
	private String email;

	@Column(name = "salary")
	private Double salary;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "tax_amount")
	private Double taxAmount;

	@Column(name = "cess_amount")
	private Double cessAmount;

	@Tolerate
	public Employee (){

	}
}
