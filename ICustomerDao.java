package com.demo.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.book.entity.Customer;


@Repository
public interface ICustomerDao extends JpaRepository<Customer, Integer>{
	
	Customer findByFullName(String fullName);
	
	
	// Native Query method
			@Query(value="delete from customer where fullname=:name", nativeQuery=true)
			void deleteByName(@Param("name") String fullName);

}
