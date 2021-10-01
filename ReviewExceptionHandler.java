package com.demo.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.book.entity.ReviewErrorResponse;

@ControllerAdvice
public class ReviewExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<ReviewErrorResponse> handleException(ReviewNotFoundException exception) {
		ReviewErrorResponse error = new ReviewErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);  //404 Not found
	}

	@ExceptionHandler
	public ResponseEntity<ReviewErrorResponse> handleException(ReviewFoundException exception) {
		ReviewErrorResponse error = new ReviewErrorResponse();
		
		error.setStatus(HttpStatus.FOUND.value());
		error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.FOUND); // 302 Found  
	}

}
