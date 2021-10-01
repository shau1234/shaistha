package com.demo.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.book.entity.Review;

public interface IReviewDao extends JpaRepository<Review, Integer>{

}
