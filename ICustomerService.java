package com.demo.book.service;

import java.util.List;

import com.demo.book.entity.Customer;
import com.demo.book.exception.CustomerNotFoundException;

public interface ICustomerService {

	List<Customer> getAllCustomers();

	Customer getCustomerByName(String fullName);

	Customer getCustomerByCustomerId(int customerId) throws CustomerNotFoundException;

	Customer addCustomer(Customer customer);

	Customer deleteCustomerByCustomerId(int customerId);

	void deleteCustomerByName(String fullName);

	Customer updateCustomer(int customerId, Customer customer);

	Customer updateCustomerName(int customerId, String newName);

	

}
