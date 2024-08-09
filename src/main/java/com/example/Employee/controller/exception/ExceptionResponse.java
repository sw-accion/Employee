package com.example.Employee.controller.exception;

import lombok.Data;

import java.util.List;

public @Data class ExceptionResponse {
	private String errorCode;
	private String errorMessage;
	private List<String> errors;
}
