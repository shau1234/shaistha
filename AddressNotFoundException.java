package com.demo.book.exception;

public class AddressNotFoundException extends Exception {
	public AddressNotFoundException() {
		super();
		
	}

	public AddressNotFoundException(String message) {
		super(message);
		
	}

	public AddressNotFoundException(Throwable cause) {
		super(cause);
		
	} 

}
