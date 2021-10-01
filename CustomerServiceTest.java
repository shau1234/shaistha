package com.demo.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.book.entity.Address;
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
	//@Disabled
	void testAddCustomer() {
		User user= new User();
		user.setRole("customer");
		
		Address addr1=new Address();
		addr1.setCity("bangalore");
		addr1.setAddress("hbyyhbfbn");
		addr1.setCity("gggbg");
		addr1.setCountry("frr");
		addr1.setPincode(55666);
		List<Address> addr=new ArrayList<Address>();
		addr.add(addr1);
		Customer customer = new Customer(76,"9686263464", "Sha", LocalDate.of(2021,7,12), user,addr);
		Customer newCustomer = customerService.addCustomer(customer);
		assertEquals(76, newCustomer.getCustomerId());
		assertEquals("Sha", newCustomer.getFullName());
		assertEquals("9686263464",newCustomer.getMobileNumber());
		assertEquals(LocalDate.of(2021, 7, 12),newCustomer.getRegisterOn());
		assertEquals("customer",newCustomer.getUser().getRole());
		assertEquals("bangalore", newCustomer.getAddresses().get(1).getCity());
		
	}
	
	@Test
	@Disabled
	void testDeleteCustomerByCustomerId() {
		Customer customer = customerService.deleteCustomerByCustomerId(54);
		assertEquals("John", customer.getFullName());	
	}
	
	@Test
	@Disabled
	void testUpdateCustomer() {
		User user=new User();
		//user.setRole("customer");
		Address addr=new Address();
		addr.setAddressId(33445);
		addr.setAddress("abcd");
		addr.setCity("bangalore");
		addr.setCountry("India");
		addr.setPincode(560046);
		
		List<Address> address=new ArrayList<Address>();
		address.add(addr);
		Customer customer = new Customer(64,"9547825865", "Saad", LocalDate.of(2021,9,29), user,address);
		Customer updatedCustomer = customerService.updateCustomer(64, customer);
		assertEquals("Saad", updatedCustomer.getFullName());
	}

}
