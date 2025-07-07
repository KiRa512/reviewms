package com.kira.reviewms.reviews.impl;


import com.kira.reviewms.reviews.Review;
import com.kira.reviewms.reviews.ReviewRepo;
import com.kira.reviewms.reviews.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class reviewServiceImpl implements ReviewService {
    // This service implementation will contain methods to handle review-related operations.
    // For example, fetching all reviews for a specific company.
    private final ReviewRepo reviewRepo;
    public reviewServiceImpl(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }
    @Override
    public List<Review> getAllReviewsByCompanyId(Long companyId) {
        // Logic to fetch reviews by company ID will be implemented here.
        List<Review> reviews =  reviewRepo.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Review createReview(Long companyId, Review review) {
        // Logic to create a new review for a specific company will be implemented here.
        if (companyId != null) {
            review.setCompanyId(companyId);
            reviewRepo.save(review);
        } else {
            throw new IllegalArgumentException("Company with ID " + companyId + " does not exist.");
        }

        return review;
    }

    @Override
    public Review getReviewById(Long reviewId) {
        // Logic to fetch a specific review by its ID will be implemented here.
         Review review = reviewRepo.findById(reviewId).orElseThrow(null);
         return review;
    }
    @Override
    public boolean deleteReview(Long reviewId) {
        // Logic to delete a specific review by its ID will be implemented here.

        Review review = reviewRepo.findById(reviewId).orElseThrow(null);
        if(review!=null) {
            reviewRepo.delete(review);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void updateReview(Long reviewId, Review review) {
        // Logic to update a specific review by its ID will be implemented here.
        Review existingReview = reviewRepo.findById(reviewId).orElseThrow(null);
        existingReview.setContent(review.getContent());
        existingReview.setRating(review.getRating());
        reviewRepo.save(existingReview);
    }
}
