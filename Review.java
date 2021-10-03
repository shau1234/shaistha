package com.demo.book.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="reviews")
public class Review {
	@Id
	@Column(name="reviewId")
	private int reviewId;
	
	
	
	@Column(name="headline")
	private String headLine;
	
	
	@Column(name="comment")
    private String comment;
	
	@Column(name=" rating")
	private double rating;
	

     LocalDate reviewOn;
//	
//    private Book book;
      //@JsonIgnore
     @ManyToOne(cascade= CascadeType.ALL)
 	@JoinColumn(name="customer_review_fk")
 	private Customer customer;

     
     
     //@JsonIgnore
   @OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="book_fk")
	Book book;

	

	public  Review () {}

				public Review(int reviewId, String headLine, String comment, double rating, LocalDate reviewOn, Customer customer,
			Book book) {
		super();
		this.reviewId = reviewId;
		this.headLine = headLine;
		this.comment = comment;
		this.rating = rating;
		this.reviewOn = reviewOn;
		this.customer = customer;
		this.book = book;
	}


		public int getReviewId() {
			return reviewId;
		}
		
		
		public void setReviewId(int reviewId) {
			this.reviewId = reviewId;
		}
		
		
		public String getHeadLine() {
			return headLine;
		}
		
		
		public void setHeadLine(String headLine) {
			this.headLine = headLine;
		}
		
		
		public String getComment() {
			return comment;
		}
		
		
		public void setComment(String comment) {
			this.comment = comment;
		}
		
		
		public double getRating() {
			return rating;
		}
		
		
		public void setRating(double rating) {
			this.rating = rating;
		}
		public LocalDate getReviewOn() {
			return reviewOn;
		}


		public void setReviewOn(LocalDate reviewOn) {
			this.reviewOn = reviewOn;
		}
		
		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}
		
		public Book getBook() {
			return book;
		}

		public void setBook(Book book) {
			this.book = book;
		}

		@Override
		public String toString() {
			return "Review [reviewId=" + reviewId + ", headLine=" + headLine + ", comment=" + comment + ", rating="
					+ rating + ", reviewOn=" + reviewOn + ", customer=" + customer + ", book=" + book + "]";
		}

		
		
		
		
		

		
		
		

}
