package com.kira.reviewms.reviews;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping()
    public ResponseEntity<List<Review>> getAllReviewsByCompanyId(@RequestParam Long companyId) {
        List<Review> reviews = reviewService.getAllReviewsByCompanyId(companyId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
        Review review = reviewService.getReviewById(reviewId);
        if (review != null) {
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Review> createReview(@RequestParam Long companyId, @RequestBody Review review) {
        Review createdReview = reviewService.createReview(companyId, review);
        URI location = URI.create("/reviews"+createdReview.getId());
        return ResponseEntity
                .created(location)
                .body(createdReview);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody Review review) {
        reviewService.updateReview(reviewId, review);
        return ResponseEntity.ok("Review updated successfully!");
    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        boolean deleted = reviewService.deleteReview(reviewId);
        if (deleted) {
            return ResponseEntity.ok("Review deleted successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
