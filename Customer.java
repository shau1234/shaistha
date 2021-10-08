package com.demo.book.entity;

import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="customer")
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int customerId;
	@Column(name="mobileNumber")
	private String mobileNumber;
	@Column(name="fullName")
	@NotEmpty
	@Size(min=3, message="customer fullname should have atleast 3 char")
	private String fullName;
	@JsonIgnore
	private LocalDate registerOn=LocalDate.now();
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_fk")
	User user;
	
	@JsonIgnore
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
	//@JoinColumn(name="customer_addr_fk", referencedColumnName = "customerId")
	private List<Address> addresses = new ArrayList<>();

	@JsonIgnore
	@OneToMany(targetEntity=Review.class, mappedBy="customer")
	//@JoinColumn(name="customer_addr_fk", referencedColumnName = "customerId")
	private List<Review> reviews = new ArrayList<>();
	
	//constructors
	public Customer() {}
	
	public Customer(int customerId, String mobileNumber,
			@NotEmpty @Size(min = 3, message = "customer fullname should have atleast 3 char") String fullName,
			LocalDate registerOn, User user, List<Address> addresses, List<Review> reviews) {
		super();
		this.customerId = customerId;
		this.mobileNumber = mobileNumber;
		this.fullName = fullName;
		this.registerOn = registerOn;
		this.user = user;
		this.addresses = addresses;
		this.reviews = reviews;
	}
	
	

	
	//getters and setters
	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public LocalDate getRegisterOn() {
		return registerOn;
	}


	public void setRegisterOn(LocalDate registerOn) {
		this.registerOn = registerOn;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	public List<Address> getAddresses() {
		return addresses;
	}


	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public List<Review> getReviews() {
		return reviews;
	}



	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}



	 //toString

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", mobileNumber=" + mobileNumber + ", fullName=" + fullName
				+ ", registerOn=" + registerOn + ", user=" + user + ", addresses=" + addresses + "]";
	}


	
	
	
	
}
