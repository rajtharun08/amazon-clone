package com.amazonclone.backend.controller;

import com.amazonclone.backend.model.Review;
import com.amazonclone.backend.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{productId}")
    public ResponseEntity<Review> addReview(
            @PathVariable Long productId,
            @RequestBody Review review
    ) {
        return ResponseEntity.ok(reviewService.addReview(productId, review));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<Review>> getReviews(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewService.getReviewsByProduct(productId));
    }
}
