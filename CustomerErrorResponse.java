package com.demo.book.entity;

import lombok.Data;

@Data
public class CustomerErrorResponse {
    
	private int status;
	
	private String message;
	
	private long timeStamp;


}
