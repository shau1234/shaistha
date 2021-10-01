package com.demo.book.service;

import java.util.List;

import com.demo.book.entity.Review;
import com.demo.book.exception.ReviewNotFoundException;

public interface IReviewService {
List<Review> listAllReviews();

	

	Review deleteReviewByReview(int reviewId);

	Review updateReview(int reviewId, Review review);

	Review getReviewByReviewId(int reviewId) throws ReviewNotFoundException;



	Review addReview(Review review);

	//Review viewReviewById(int reviewId);

//	Review updateReview(int reviewId, Review review);
//	Review viewReview(Review review);
////	List<Review> listAllReviewsByBook(Book book);
////    List<Review> listAllReviewsByCustomer(Customer c);
////	List<Book> listMostFavoredBooks();
////	List<Review> listAllReviewByCustomer(Customer customer);
////	
//	
//	//Object getAllReviews();
//	Review deleteReviewByReviewId(int reviewId);
//	//Review deleteReviewByReviewId(int reviewId);
//	Review updateReview(Review review);
//	Review updateReview(int reviewId, int reviewId2, Review review);
//	Review listAllReviewsByBook(Review review, Review review2);

}
