package com.demo.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.book.entity.Address;
import com.demo.book.entity.Customer;
import com.demo.book.exception.CustomerNotFoundException;
import com.demo.book.service.ICustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
	@Autowired
	ICustomerService customerService;
	private static Logger logger = LogManager.getLogger();
	
	//get all customers
	@GetMapping("/customers")
	ResponseEntity<List<Customer>> getAllCustomers() {
		logger.info("Sending request to service layer for getting customers");
		return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK); // 200 Ok
	}
	
	// Get customer by customerId - GET
			@GetMapping("/customers/customerId/{customerId}")
			ResponseEntity<Customer> getCustomerByCustomerId(@PathVariable("customerId") int customerId) throws CustomerNotFoundException  {
				logger.info("Sending request to service layer to get customer by customerId");
				Customer customer = customerService.getCustomerByCustomerId(customerId);
				logger.info("Returning customer object");
				return new ResponseEntity<>(customer, HttpStatus.OK); // 200 Ok
				
			}
	
	// Get customer by Name - GET
		@GetMapping("/customers/name/{cusName}")
		ResponseEntity<Customer> getCustomerByName(@PathVariable("cusName") String fullName) {
			Customer customer = customerService.getCustomerByName(fullName);
			return new ResponseEntity<>(customer, HttpStatus.OK);
		}
		
		// Add new customer - POST
		@PostMapping("/customers")
		ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
			Customer cus = customerService.addCustomer(customer);
			return new ResponseEntity<>(cus, HttpStatus.CREATED); // 201 created
		}
		
		// Delete customer by customerId - DELETE
		@DeleteMapping("/customers/{customerId}")
		ResponseEntity<Customer> deleteCustomerByCustomerId(@PathVariable("customerId") int customerId) {
			Customer customer= customerService.deleteCustomerByCustomerId(customerId);
			return new ResponseEntity<>(customer, HttpStatus.OK);
		}
		
		//Delete customer by name
		@DeleteMapping("/customers/delete/{fullName}")
		ResponseEntity<Void> deleteCustomer(@PathVariable("fullName") String fullName) {
			customerService.deleteCustomerByName(fullName);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		// Update customer - PUT
		@PutMapping("/customers/{customerId}")
		ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") int customerId, @RequestBody Customer customer) {
		 Customer cus = customerService.updateCustomer(customerId, customer);
		 return new ResponseEntity<>(cus, HttpStatus.OK);
		}
		
		// update student name - PATCH
		@PatchMapping("/customers/{customerId}")
		ResponseEntity<Customer> updateCustomerName(@PathVariable("customerId") int customerId, @RequestBody String newName) {
			Customer customer = customerService.updateCustomerName(customerId, newName);
			return new ResponseEntity<>(customer, HttpStatus.OK);
		}
		
		@PatchMapping("/customers/updateAddr/{customerId}")
		ResponseEntity<Customer> updateCustomerAddress(@PathVariable("customerId") int customerId, @RequestBody List<Address> addresses) throws CustomerNotFoundException {
			return new ResponseEntity<>(customerService.updateCustomerAddress(customerId, addresses), HttpStatus.OK);
		}
		


}
