package com.demo.book.service;

import java.util.List;

import com.demo.book.dto.UserDto;
import com.demo.book.entity.User;
import com.demo.book.exception.InvalidCredentialsException;
import com.demo.book.exception.UserNotFoundException;

public interface IUserService {

	List<User> getAllUsers();
	User addUser(User user);
	
	
	User getUserByUserId(int userId);
	UserDto login(User user) throws UserNotFoundException, InvalidCredentialsException;
	UserDto logout(User user) throws UserNotFoundException;
	User deleteUserByUserId(int userId);
	User updateUser(int userId, User user);

}
