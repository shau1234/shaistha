package com.demo.book.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.book.dao.IUserDao;
import com.demo.book.dto.UserDto;
import com.demo.book.entity.Customer;
import com.demo.book.entity.User;
import com.demo.book.exception.InvalidCredentialsException;
import com.demo.book.exception.UserNotFoundException;


@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	IUserDao userDao;
	
	@Override
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public User addUser(User user) {
		return userDao.save(user);
	}


	@Override
	public User getUserByUserId(int userId) {
		
		return userDao.findById(userId).get();
	}

	@Override
	public UserDto login(User user) throws UserNotFoundException, InvalidCredentialsException {
		Optional<User> dbUser=userDao.findByEmail(user.getEmail());
		if(!dbUser.isPresent()) {
			throw new UserNotFoundException("user not found with given email "+user.getEmail());
			
		}
		
		User u = dbUser.get();
		UserDto userDto=new UserDto();
		if(user.getPassword().equals(u.getPassword())&& user.getRole().equals(u.getRole()))
		{
			u.setLoggedIn(true);
			userDao.save(u);
			userDto.setEmail(u.getEmail());
			userDto.setRole(u.getRole());
			userDto.setLoggedIn(true);
		}
		else {
			throw new InvalidCredentialsException("Invalid credentials");
		}
		
		
		return userDto;
	}
	
	@Override
	public UserDto logout(User user) throws UserNotFoundException {
		Optional<User> dbUser=userDao.findByEmail(user.getEmail());
		if(!dbUser.isPresent()) {
			throw new UserNotFoundException("user not found with given email "+user.getEmail());
		}
		User u = dbUser.get();
		UserDto userDto=new UserDto();
		if(user.getPassword().equals(u.getPassword())&& user.getRole().equals(u.getRole()))
		{
			
			u.setLoggedIn(false);
			userDao.save(u);
			userDto.setLoggedIn(false);
		}
		return userDto;
	}


	@Override
	public User deleteUserByUserId(int userId) {
		User usr = userDao.findById(userId).get();
		userDao.deleteById(userId);
		return usr;
	}

	@Override
	public User updateUser(int userId, User user) {
		User usr = userDao.findById(userId).get();
		usr.setEmail(user.getEmail());
		usr.setPassword(user.getPassword());
		usr.setRole(user.getRole());
		usr.setLoggedIn(user.isLoggedIn());
		return userDao.save(usr) ;
		
	}

	
	
	

}
