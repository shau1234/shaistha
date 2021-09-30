package com.demo.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.book.entity.Customer;
import com.demo.book.entity.User;

@SpringBootTest
public class CustomerServiceTest {
	@Autowired
	ICustomerService customerService;
	
	@Test
	@Disabled
	void testGetAllCustomers() {
		List<Customer> customers = customerService.getAllCustomers(); 
		assertEquals(3, customers.size());
		assertEquals("Sam", customers.get(2).getFullName());
	}
	
	@Test
	@Disabled
	void testAddCustomer() {
		User user= new User();
		user.setRole("customer");
		Customer customer = new Customer(64,"9686263464", "Sha", LocalDate.of(2021,7,12), user);
		Customer newCustomer = customerService.addCustomer(customer);
		assertEquals(64, newCustomer.getCustomerId());
		assertEquals("Sha", newCustomer.getFullName());
		assertEquals("9686263464",newCustomer.getMobileNumber());
		assertEquals(LocalDate.of(2021, 7, 12),newCustomer.getRegisterOn());
		assertEquals("customer",newCustomer.getUser().getRole());
		
	}
	
	@Test
	@Disabled
	void testDeleteCustomerByCustomerId() {
		Customer customer = customerService.deleteCustomerByCustomerId(54);
		assertEquals("John", customer.getFullName());	
	}
	
	@Test
	void testUpdateCustomer() {
		User user=new User();
		//user.setRole("customer");
		Customer customer = new Customer(64,"9547825865", "Zain", LocalDate.of(2021,9,29), user);
		Customer updatedCustomer = customerService.updateCustomer(64, customer);
		assertEquals("Zain", updatedCustomer.getFullName());
	}

}
