package com.demo.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.demo.book.entity.User;
import com.demo.book.exception.UserNotFoundException;

@SpringBootTest
public class UserServiceTest {
	@Autowired
	IUserService userService;
	
	@Test
	void testGetUserByUserId() throws UserNotFoundException {
		
		User usr= userService.getUserByUserId(30);
		assertEquals(30, usr.getUserId());
		assertEquals("sha@gmail.com", usr.getEmail());
		assertEquals("abcd", usr.getPassword());
		assertEquals("customer", usr.getRole());
		assertEquals(false, usr.isLoggedIn());
	}
	
	@Test
	@Disabled
	void testGetAllUsers() {
		
		List<User> users = userService.getAllUsers();
		assertEquals(10, users.size());
		assertEquals("sha@gmail.com", users.get(0).getEmail());
		
		
	}
	
	@Test
	@Disabled
	void testAddUser() {
		User user = new User();
		user.setUserId(60);
		user.setEmail("ad@gmail.com");
		user.setPassword("5678");
		user.setRole("customer");
		user.setLoggedIn(false);
		User newUser = userService.addUser(user);
		
		assertEquals(60, newUser.getUserId());
		assertEquals("ad@gmail.com", newUser.getEmail());
		assertEquals("5678", newUser.getPassword());
		assertEquals("customer", newUser.getRole());
		assertEquals(false, newUser.isLoggedIn());
		
	}
	
	@Test
	//@Disabled
	void testUpdateUser() {
		User user=new User();
		user.setUserId(60);
		user.setEmail("harsh@gmail.com");
		user.setPassword("12ab");
		user.setRole("customer");
		user.setLoggedIn(false);
		
		User updatedUser = userService.updateUser(60, user);
		assertEquals("harsh@gmail.com", updatedUser.getEmail());
	}
	
	@Test
	@Disabled
	void testDeleteUserByUserId() {
		User user = userService.deleteUserByUserId(38);
		assertEquals("harsh@gmail.com", user.getEmail());	
	}
	

}
