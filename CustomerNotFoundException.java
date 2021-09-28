package com.demo.book.exception;

public class CustomerNotFoundException extends Exception{
	public CustomerNotFoundException() {
		super();
		
	}

	public CustomerNotFoundException(String message) {
		super(message);
		
	}

	public CustomerNotFoundException(Throwable cause) {
		super(cause);
		
	}

}
