package com.amazonclone.backend.service;

import com.amazonclone.backend.model.Product;
import com.amazonclone.backend.model.Review;
import com.amazonclone.backend.repository.ProductRepository;
import com.amazonclone.backend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    public Review addReview(Long productId, Review review) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        review.setProduct(product);
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByProduct(Long productId) {
        return reviewRepository.findByProductId(productId);
    }
}
