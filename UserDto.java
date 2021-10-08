package com.demo.book.dto;

import lombok.Data;

@Data
public class UserDto {
	private int userId;
	private String email;
	private String role;
	private boolean isLoggedIn;

}
