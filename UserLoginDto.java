package com.demo.book.dto;

import lombok.Data;

@Data

public class UserLoginDto {
	private String email;
	private String password;
	private String role;

}
