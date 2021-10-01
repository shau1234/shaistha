package com.demo.book.exception;

public class UserNotFoundException extends Exception {
	public UserNotFoundException() {
		super();
		
	}

	public UserNotFoundException(String message) {
		super(message);
		
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
		
	}

}
