package com.demo.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.demo.book.dao.IUserDao;
import com.demo.book.entity.User;
import com.demo.book.exception.UserNotFoundException;

@ExtendWith(SpringExtension.class)
public class UserServiceMockitoTest {
	@InjectMocks
	UserServiceImpl userService;
	
	@MockBean
	IUserDao userDao;

	// Initialization of mock objects
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testGetUserByUserId() throws UserNotFoundException {
		
		User user = new User();
		user.setUserId(38);
		user.setEmail("sam@gmail.com");
		user.setPassword("1234");
		user.setRole("customer");
		user.setLoggedIn(false);
		Mockito.when(userDao.findById(38)).thenReturn(Optional.of(user));
		User usr= userService.getUserByUserId(38);
		assertEquals(38, usr.getUserId());
		assertEquals("sam@gmail.com", usr.getEmail());
		assertEquals("1234", usr.getPassword());
		assertEquals("customer", usr.getRole());
		assertEquals(false, usr.isLoggedIn());
	}
	
	@Test
	void testGetAllCustomers() {
		User user1 = new User();
		user1.setUserId(40);
		user1.setEmail("ad@gmail.com");
		user1.setPassword("abcd");
		user1.setRole("customer");
		user1.setLoggedIn(false);
		
		User user2 = new User();
		user2.setUserId(41);
		user2.setEmail("shau@gmail.com");
		user2.setPassword("12ab");
		user2.setRole("customer");
		user2.setLoggedIn(false);
		
		List<User> userList = new ArrayList<>();
		userList.add(user1);
		userList.add(user2);
		Mockito.when(userDao.findAll()).thenReturn(userList);
		List<User> users = userService.getAllUsers();
		assertEquals(2, users.size());
		assertEquals(40, user1.getUserId());
		assertEquals("ad@gmail.com", user1.getEmail());
		assertEquals("abcd", user1.getPassword());
		assertEquals("customer", user1.getRole());
		assertEquals(false, user1.isLoggedIn());
		
		assertEquals(41, user2.getUserId());
		assertEquals("shau@gmail.com", user2.getEmail());
		assertEquals("12ab", user2.getPassword());
		assertEquals("customer", user2.getRole());
		assertEquals(false, user2.isLoggedIn());
		
	}
	
	@Test
	
	void testAddUser() {
		User user = new User();
		user.setUserId(44);
		user.setEmail("harsh@gmail.com");
		user.setPassword("5678");
		user.setRole("customer");
		user.setLoggedIn(false);
		Mockito.when(userDao.save(user)).thenReturn(user);
		User newUser = userService.addUser(user);
		
		assertEquals(44, newUser.getUserId());
		assertEquals("harsh@gmail.com", newUser.getEmail());
		assertEquals("5678", newUser.getPassword());
		assertEquals("customer", newUser.getRole());
		assertEquals(false, newUser.isLoggedIn());
		
	}
	
	@Test
	void testUpdateUser() {
		User user=new User();
		user.setUserId(44);
		user.setEmail("harsh@gmail.com");
		user.setPassword("5678");
		user.setRole("customer");
		user.setLoggedIn(false);
		
		Mockito.when(userDao.findById(44)).thenReturn(Optional.of(user));
		Mockito.when(userDao.save(user)).thenReturn(user);
		User updatedUser = userService.updateUser(44, user);
		assertEquals("harsh@gmail.com", updatedUser.getEmail());
	}
	
	@Test
	void testDeleteUser() {
		User user=new User();
		user.setUserId(38);
		user.setEmail("shweta@gmail.com");
		user.setPassword("1234");
		user.setRole("customer");
		user.setLoggedIn(false);
		Mockito.when(userDao.findById(38)).thenReturn(Optional.of(user));
		userDao.deleteById(38);
		User usr = userService.deleteUserByUserId(38);
		assertEquals("shweta@gmail.com", usr.getEmail());
	}

}
