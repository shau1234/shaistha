package com.demo.book.service;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.demo.book.dao.ICustomerDao;
import com.demo.book.entity.Customer;
import com.demo.book.entity.User;
import com.demo.book.exception.CustomerNotFoundException;

@ExtendWith(SpringExtension.class)
public class CustomerServiceMockitoTest {
	// @InjectMock - Creates instance of a class and injects mocks that are created
		// with @Mock

		@InjectMocks
		CustomerServiceImpl customerService;
		
		// @MockBean - Creates Mock of a class or interface

		@MockBean
		ICustomerDao customerDao;

		// Initialization of mock objects
		@BeforeEach
		void init() {
			MockitoAnnotations.openMocks(this);
		}
		
		@Test
		@Disabled
		void testGetCustomerByCustomerId() throws CustomerNotFoundException {
			User user=new User();
			user.setEmail("sam@gmail.com");
			user.setPassword("1234");
			user.setRole("customer");
			user.setUserId(42);
			Customer customer = new Customer(41,"9547825865", "Sam", LocalDate.of(2021,9,29), user);
			
			Mockito.when(customerDao.findById(41)).thenReturn(Optional.of(customer));
			Customer cus= customerService.getCustomerByCustomerId(41);
			assertEquals(41, cus.getCustomerId());
			assertEquals("Sam", cus.getFullName());
			assertEquals("9547825865",cus.getMobileNumber());
			assertEquals(LocalDate.of(2021, 9, 29),cus.getRegisterOn());
			assertEquals(42,cus.getUser().getUserId());
		}
		
		@Test
		void testGetAllCustomers() {
			User user1=new User();
			user1.setEmail("sita@gmail.com");
			User user2=new User();
			user2.setEmail("suresh@gmail.com");
			Customer cus1 = new Customer(37,"9547827465", "Sita", LocalDate.of(2021,9,29), user1);
			Customer cus2 = new Customer(39,"8621425865", "Suresh", LocalDate.of(2021,9,28), user2);
			List<Customer> customerList = new ArrayList<>();
			customerList.add(cus1);
			customerList.add(cus2);
			Mockito.when(customerDao.findAll()).thenReturn(customerList);
			List<Customer> customers = customerService.getAllCustomers();
			assertEquals(2, customers.size());
			assertEquals(37, cus1.getCustomerId());
			assertEquals("Sita", cus1.getFullName());
			assertEquals("9547827465",cus1.getMobileNumber());
			assertEquals(LocalDate.of(2021, 9, 29),cus1.getRegisterOn());
			assertEquals("sita@gmail.com",cus1.getUser().getEmail());
			
			assertEquals(39, cus2.getCustomerId());
			assertEquals("Suresh", cus2.getFullName());
			assertEquals("8621425865",cus2.getMobileNumber());
			assertEquals(LocalDate.of(2021, 9, 28),cus2.getRegisterOn());
			assertEquals("suresh@gmail.com",cus2.getUser().getEmail());
		}
		
		@Test
		void testAddCustomer() {
			User user=new User();
			user.setRole("customer");
			Customer customer = new Customer(43,"8904509025","Charan", LocalDate.of(2021,8,25), user);
			Mockito.when(customerDao.save(customer)).thenReturn(customer);
			Customer newCustomer = customerService.addCustomer(customer);
			assertEquals(43, newCustomer.getCustomerId());
			assertEquals("Charan", newCustomer.getFullName());
			assertEquals("8904509025",newCustomer.getMobileNumber());
			assertEquals(LocalDate.of(2021, 8, 25),newCustomer.getRegisterOn());
			assertEquals("customer",newCustomer.getUser().getRole());
		}
		
		@Test
		void testUpdateCustomer() {
			User user=new User();
			//user.setRole("customer");
			Customer customer = new Customer(41,"9547825865", "Tanu", LocalDate.of(2021,9,29), user);
			Mockito.when(customerDao.findById(41)).thenReturn(Optional.of(customer));
			Mockito.when(customerDao.save(customer)).thenReturn(customer);
			Customer updatedCustomer = customerService.updateCustomer(41, customer);
			assertEquals("Tanu", updatedCustomer.getFullName());
		}
		
		@Test
		void testDeleteCustomer() {
			User user=new User();
			Customer customer = new Customer(41,"9547825865", "Tanu", LocalDate.of(2021,9,29), user);
			Mockito.when(customerDao.findById(41)).thenReturn(Optional.of(customer));
			customerDao.deleteById(41);
			Customer cus = customerService.deleteCustomerByCustomerId(41);
			assertEquals("Tanu", cus.getFullName());
		}

}
