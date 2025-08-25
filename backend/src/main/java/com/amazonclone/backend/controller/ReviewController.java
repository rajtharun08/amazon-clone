package com.amazonclone.backend.controller;

import com.amazonclone.backend.model.Review;
import com.amazonclone.backend.model.Product;
import com.amazonclone.backend.repository.ReviewRepository;
import com.amazonclone.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    @PostMapping("/{productId}")
    public ResponseEntity<Review> addReview(@PathVariable Long productId, @RequestBody Review review) {
        return productRepository.findById(productId)
                .map(product -> {
                    review.setProduct(product);
                    Review saved = reviewRepository.save(review);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{productId}")
    public List<Review> getReviews(@PathVariable Long productId) {
        return reviewRepository.findByProductId(productId);
    }
}

