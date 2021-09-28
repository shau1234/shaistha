package com.demo.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.book.entity.CustomerErrorResponse;

@ControllerAdvice
public class CustomerExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exception) {
		CustomerErrorResponse error = new CustomerErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);  //404 Not found
	}

	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerFoundException exception) {
		CustomerErrorResponse error = new CustomerErrorResponse();
		
		error.setStatus(HttpStatus.FOUND.value());
		error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.FOUND); // 302 Found  
	}

}
