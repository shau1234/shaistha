package com.demo.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.book.dto.UserDto;
import com.demo.book.dto.UserLoginDto;
import com.demo.book.entity.Customer;
import com.demo.book.entity.User;
import com.demo.book.exception.InvalidCredentialsException;
import com.demo.book.exception.UserNotFoundException;
import com.demo.book.service.IUserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	@Autowired
	IUserService userService;
	private static Logger logger = LogManager.getLogger();
	
	@GetMapping("/users")
	ResponseEntity<List<UserDto>> getAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	// Get Users by user id - GET
		@GetMapping("/users/{userId}")
		ResponseEntity<User> getUserByUserId(@PathVariable("userId") int userId) {
		 User user = userService.getUserByUserId(userId);
		 return new ResponseEntity<>(user, HttpStatus.OK); // 200 Ok
			
		}
		
		// Add new user - POST
				@PostMapping("/users")
				ResponseEntity<User> addUser(@RequestBody User user) {
					User u = userService.addUser(user);
					return new ResponseEntity<>(u, HttpStatus.CREATED); // 201 created
				}
				
				// Delete user by userId - DELETE
				@DeleteMapping("/users/{userId}")
				ResponseEntity<User> deleteUserByUserId(@PathVariable("userId") int userId) {
					User user= userService.deleteUserByUserId(userId);
					return new ResponseEntity<>(user, HttpStatus.OK);
				}
				
				// Update user - PUT
				@PutMapping("/users/{userId}")
				ResponseEntity<User> updateUser(@PathVariable("userId") int userId, @RequestBody User user) {
				 User usr = userService.updateUser(userId, user);
				 return new ResponseEntity<>(usr, HttpStatus.OK);
				}
				
				// Login user - POST
				@PostMapping("/login")
				ResponseEntity<UserDto> login(@RequestBody UserLoginDto user) throws UserNotFoundException, InvalidCredentialsException {
					UserDto u = userService.login(user);
					return new ResponseEntity<>(u, HttpStatus.CREATED); // 201 created
				}
				
				// logout user - PATCH
				@PatchMapping("/logout/{email}")
				ResponseEntity<UserDto> logout( @PathVariable("email") String email) throws UserNotFoundException, InvalidCredentialsException {
					logger.info(email);
					UserDto u = userService.logout(email);
					return new ResponseEntity<>(u, HttpStatus.OK);
				}

}
