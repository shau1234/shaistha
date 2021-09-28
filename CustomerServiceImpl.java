package com.demo.book.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.book.dao.ICustomerDao;
import com.demo.book.entity.Customer;
import com.demo.book.exception.CustomerFoundException;
import com.demo.book.exception.CustomerNotFoundException;


@Service
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	ICustomerDao customerDao;
	
	@Override
	public List<Customer> getAllCustomers() {
		return customerDao.findAll();
	}
	
	@Override
	public Customer getCustomerByCustomerId(int customerId) throws CustomerNotFoundException {
		Optional<Customer> customer = customerDao.findById(customerId);
		if(!customer.isPresent()) {
			throw new CustomerNotFoundException("Customer not found with given customerId "+customerId);
		}
		return customer.get();
	}
	
	@Override
	public Customer addCustomer(Customer customer) {
		Optional<Customer> cus = customerDao.findById(customer.getCustomerId());
		if(cus.isPresent()) {
			throw new CustomerFoundException("Customer already exists with given customerId "+customer.getCustomerId());
		}
		return customerDao.save(customer);
	}
	
	@Override
	public Customer updateCustomer(int customerId, Customer customer) {
		Customer cus = customerDao.findById(customerId).get();
		cus.setFullName(customer.getFullName());
		
		cus.setMobileNumber(customer.getMobileNumber());
		cus.setRegisterOn(customer.getRegisterOn());
		return customerDao.save(cus) ;
	}
	
	@Override
	public Customer deleteCustomerByCustomerId(int customerId) {
		Customer cus = customerDao.findById(customerId).get();
		customerDao.deleteById(customerId);
		return cus;
	}

	@Override
	public Customer updateCustomerName(int customerId, String newName) {
		Customer cus = customerDao.findById(customerId).get();
		cus.setFullName(newName);
		return customerDao.save(cus);
	}
	
	@Override
	public Customer getCustomerByName(String fullName) {
		Customer customer = customerDao.findByFullName(fullName);
		return customer;
	}


	@Override
	public void deleteCustomerByName(String fullName) {
		customerDao.deleteByName(fullName);
		
	}

	

}
