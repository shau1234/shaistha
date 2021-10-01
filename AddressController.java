package com.demo.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.book.entity.Address;
import com.demo.book.exception.AddressNotFoundException;
import com.demo.book.service.IAddressService;

@RestController
public class AddressController {
	@Autowired
	IAddressService addressService;
	
	@GetMapping("/address")
	ResponseEntity<List<Address>>ListAllAddress() {
		return new ResponseEntity<>(addressService.ListAllAddress(),HttpStatus.OK);
	}

	@GetMapping("/address/{addressId}")
	ResponseEntity<Address> getAddressByAddressId(@PathVariable("addressId") int addressId) throws AddressNotFoundException {
	   Address address = addressService.getAddressByAddressId(addressId);
        return new ResponseEntity<>(address, HttpStatus.OK);// 200 Ok
	}
		// Add new address
	@PostMapping("/address")
	ResponseEntity<Address> addAdress(@RequestBody Address address) {
		Address add =  addressService.addAddress(address);
		return new ResponseEntity<>(add, HttpStatus.CREATED); 
	}
	
	@DeleteMapping("/address/{addressId}")
    ResponseEntity<Address> deleteAddressByaddress(@PathVariable("addressId") int addressId) {
		Address address= addressService.deleteAddressByAddress(addressId);
	return new ResponseEntity<>(address, HttpStatus.OK);
	}
	 @PutMapping("/address/{addressId}")
     ResponseEntity<Address> updateAddress(@PathVariable("addressId") int addressId, @RequestBody Address address) {
     Address adds = addressService.updateAddress(addressId, address);
     return new ResponseEntity<>(adds, HttpStatus.OK);
}

}
