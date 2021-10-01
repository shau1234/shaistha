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
import com.demo.book.entity.Address;
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
		void testGetCustomerByCustomerId() throws CustomerNotFoundException {
			User user=new User();
			user.setEmail("sam@gmail.com");
			user.setPassword("1234");
			user.setRole("customer");
			user.setUserId(42);
			
			Address addr=new Address();
			addr.setAddressId(33445);
			addr.setAddress("abcd");
			addr.setCity("bangalore");
			addr.setCountry("India");
			addr.setPincode(560046);
		
			List<Address> address=new ArrayList<Address>();
			 address.add(addr);
			Customer customer = new Customer(41,"9547825865", "Sam", LocalDate.of(2021,9,29), user,address);
			
			Mockito.when(customerDao.findById(41)).thenReturn(Optional.of(customer));
			Customer cus= customerService.getCustomerByCustomerId(41);
			assertEquals(41, cus.getCustomerId());
			assertEquals("Sam", cus.getFullName());
			assertEquals("9547825865",cus.getMobileNumber());
			assertEquals(LocalDate.of(2021, 9, 29),cus.getRegisterOn());
			assertEquals(42,cus.getUser().getUserId());
			assertEquals(33445, cus.getAddresses().get(0).getAddressId());
		}
		
		@Test
		//@Disabled
		void testGetAllCustomers() {
			User user1=new User();
			user1.setEmail("sita@gmail.com");
			User user2=new User();
			user2.setEmail("suresh@gmail.com");
			
			Address addr1=new Address();
			addr1.setCity("bangalore");
			Address addr2=new Address();
			addr2.setCity("Mysore");
			
			List<Address> addr=new ArrayList<Address>();
			addr.add(addr1);
			addr.add(addr2);
			
			Customer cus1 = new Customer(37,"9547827465", "Sita", LocalDate.of(2021,9,29), user1,addr);
			Customer cus2 = new Customer(39,"8621425865", "Suresh", LocalDate.of(2021,9,28), user2,addr);
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
			assertEquals("bangalore", cus1.getAddresses().get(0).getCity());
			
			assertEquals(39, cus2.getCustomerId());
			assertEquals("Suresh", cus2.getFullName());
			assertEquals("8621425865",cus2.getMobileNumber());
			assertEquals(LocalDate.of(2021, 9, 28),cus2.getRegisterOn());
			assertEquals("suresh@gmail.com",cus2.getUser().getEmail());
			assertEquals("Mysore", cus2.getAddresses().get(1).getCity());
		}
		
		@Test
		//@Disabled
		void testAddCustomer() {
			User user=new User();
			user.setRole("customer");
			
			Address addr=new Address();
			addr.setPincode(560046);
			List<Address> address=new ArrayList<Address>();
			address.add(addr);
			
			Customer customer = new Customer(43,"8904509025","Charan", LocalDate.of(2021,8,25), user,address);
			Mockito.when(customerDao.save(customer)).thenReturn(customer);
			Customer newCustomer = customerService.addCustomer(customer);
			assertEquals(43, newCustomer.getCustomerId());
			assertEquals("Charan", newCustomer.getFullName());
			assertEquals("8904509025",newCustomer.getMobileNumber());
			assertEquals(LocalDate.of(2021, 8, 25),newCustomer.getRegisterOn());
			assertEquals("customer",newCustomer.getUser().getRole());
			assertEquals(560046, newCustomer.getAddresses().get(0).getPincode());
		}
		
		@Test
		void testUpdateCustomer() {
			User user=new User();
			//user.setRole("customer");
			List<Address> addr=new ArrayList<Address>();
			Customer customer = new Customer(41,"9547825865", "Tanu", LocalDate.of(2021,9,29), user,addr);
			Mockito.when(customerDao.findById(41)).thenReturn(Optional.of(customer));
			Mockito.when(customerDao.save(customer)).thenReturn(customer);
			Customer updatedCustomer = customerService.updateCustomer(41, customer);
			assertEquals("Tanu", updatedCustomer.getFullName());
		}
		
		@Test
		void testDeleteCustomer() {
			User user=new User();
			List<Address> addr=new ArrayList<Address>();
			Customer customer = new Customer(41,"9547825865", "Tanu", LocalDate.of(2021,9,29), user,addr);
			Mockito.when(customerDao.findById(41)).thenReturn(Optional.of(customer));
			customerDao.deleteById(41);
			Customer cus = customerService.deleteCustomerByCustomerId(41);
			assertEquals("Tanu", cus.getFullName());
		}

}
