package com.demo.book.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.book.dao.IReviewDao;
import com.demo.book.entity.Review;
import com.demo.book.exception.ReviewNotFoundException;

@Service
public class ReviewServiceImpl implements IReviewService{
    
	
	@Autowired
	IReviewDao reviewDao;

	@Override
	public List<Review> listAllReviews() {
		return reviewDao.findAll();
	}

	@Override
	public Review getReviewByReviewId(int reviewId) throws ReviewNotFoundException {
		Optional<Review> review = reviewDao.findById(reviewId);
		if(!review.isPresent()) {
			throw new ReviewNotFoundException("Review not found with given ReviewId "+reviewId);
		}
		return review.get();
	}
	
	@Override
	public Review addReview(Review review) {
		return reviewDao.save(review);
	}
	
  @Override
  public Review deleteReviewByReview(int reviewId) {
	   Review rev = reviewDao.findById(reviewId).get();
	   reviewDao.deleteById(reviewId);
		return rev;
	  
	}
    @Override
    	public Review updateReview(int reviewId, Review review) {
   	 Review rev = reviewDao.findById(reviewId).get();
		rev.setComment(review.getComment());
		rev.setHeadLine(review.getHeadLine());
		return reviewDao.save(rev) ;
    }

//	public List<Review> getAllReview() {
//		// TODO Auto-generated method stub
//		return null;
//	}
    
//  @Override 
//  public Review viewReviewById(int reviewId ) {
//  Review rev=reviewDao.findById(reviewId).get();
//   return rev;
//}

}
