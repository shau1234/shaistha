package com.demo.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.book.entity.Address;

public interface IAddressDao extends JpaRepository<Address, Integer>{

}
