package com.demo.book.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.book.dao.IUserDao;
import com.demo.book.dto.UserDto;
import com.demo.book.dto.UserLoginDto;
import com.demo.book.entity.Customer;
import com.demo.book.entity.User;
import com.demo.book.exception.InvalidCredentialsException;
import com.demo.book.exception.UserNotFoundException;


@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	IUserDao userDao;
	private static Logger logger = LogManager.getLogger();
	
	@Override
	public List<UserDto> getAllUsers() {
		List<User> usr=userDao.findAll();
		List<UserDto> userDtos=new ArrayList<>();
		for(User u:usr) {
			UserDto usrDto= new UserDto();
			usrDto.setUserId(u.getUserId());
			usrDto.setEmail(u.getEmail());
			usrDto.setRole(u.getRole());
			userDtos.add(usrDto);
		}
		return userDtos;
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
	public UserDto login(UserLoginDto user) throws UserNotFoundException, InvalidCredentialsException {
		Optional<User> dbUser=userDao.findByEmail(user.getEmail());
		if(!dbUser.isPresent()) {
			throw new InvalidCredentialsException("Invalid Credentials");
			
		}
		
		User u = dbUser.get();
		
		if(user.getEmail().equalsIgnoreCase(u.getEmail())&& 
				user.getPassword().equalsIgnoreCase(u.getPassword())&& 
				user.getRole().equalsIgnoreCase(u.getRole()))
		{
			u.setLoggedIn(true);
			userDao.save(u);
			UserDto userDto=new UserDto();
			userDto.setEmail(u.getEmail());
			userDto.setRole(u.getRole());
			userDto.setLoggedIn(true);
			return userDto;
		}
		else {
			throw new InvalidCredentialsException("Invalid credentials");
		}
		
	}
	
	@Override
	public UserDto logout(String email) throws UserNotFoundException,InvalidCredentialsException {
		logger.info(email);
		Optional<User> dbUser=userDao.findByEmail(email);
		if(!dbUser.isPresent()) {
			throw new InvalidCredentialsException("Invalid Credentials");
		}
		User u = dbUser.get();
		logger.info(u.getEmail());
		u.setLoggedIn(false);
		userDao.save(u);
		UserDto userDto=new UserDto();
		userDto.setLoggedIn(false);
		
		
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
