package com.demo.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.demo.book.entity.UserErrorResponse;

@ControllerAdvice
public class UserExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<UserErrorResponse> handleException(UserNotFoundException exception) {
		UserErrorResponse error = new UserErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);  //404 Not found
	}

}
