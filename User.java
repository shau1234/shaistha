package com.demo.book.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User {
	@Id
	@GeneratedValue
	private int userId;
	private String email;
	private String password;
	private String role;
	@JsonIgnore
	private boolean isLoggedIn=false;
	@JsonIgnore
	@OneToOne(mappedBy="user",cascade=CascadeType.ALL)
	Customer customer;

}
