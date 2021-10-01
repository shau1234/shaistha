package com.demo.book.dao;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.book.entity.User;

@Repository
public interface IUserDao extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);

	

}
