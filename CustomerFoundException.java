package com.demo.book.exception;

public class CustomerFoundException extends RuntimeException{
	public CustomerFoundException() {
		super();
	}
	public CustomerFoundException(String message) {
		super(message);
		
	}

	public CustomerFoundException(Throwable cause) {
		super(cause);
		
	}

}
