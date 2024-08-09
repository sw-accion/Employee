package com.example.Employee.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	private final Logger log = LoggerFactory.getLogger (this.getClass ());

	//private I18NMessageHandler messageHandler;

	private GlobalExceptionHandler () {
		super ();
	}


	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ExceptionResponse> badRequest (ValidationException ex) {
		ExceptionResponse response = new ExceptionResponse ();
		response.setErrorCode ("Bad Request");
		response.setErrorMessage (ex.getMessage ());
		return new ResponseEntity<ExceptionResponse> (response, HttpStatus.BAD_REQUEST);
	}

}
