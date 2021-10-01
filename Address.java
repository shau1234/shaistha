package com.demo.book.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
@Entity
@Table(name="address")
public class Address {
	@Id
	@GeneratedValue
	private int addressId;
	private String address;
	private String city;
	private String country;
	private int pincode;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="customer_addr_fk")
	private Customer customer;

}
