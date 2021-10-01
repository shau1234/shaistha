package com.demo.book.exception;

public class ReviewNotFoundException extends Exception {
	public ReviewNotFoundException() {
		super();
		
	}

	public ReviewNotFoundException(String message) {
		super(message);
		
	}

	public ReviewNotFoundException(Throwable cause) {
		super(cause);
		
	}

}
