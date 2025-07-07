package com.kira.reviewms.reviews;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviewsByCompanyId(Long companyId);
    Review createReview(Long companyId, Review review);
    Review getReviewById(Long reviewId);
    boolean deleteReview(Long reviewId);
    void updateReview(Long reviewId, Review review);
}
