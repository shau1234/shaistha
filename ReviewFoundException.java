package com.demo.book.exception;

public class ReviewFoundException extends RuntimeException {
	public ReviewFoundException() {
		super();
	}
	public ReviewFoundException(String message) {
		super(message);
		
	}

	public ReviewFoundException(Throwable cause) {
		super(cause);
		
	}

}
