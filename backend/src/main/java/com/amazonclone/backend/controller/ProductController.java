package com.amazonclone.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.amazonclone.backend.model.Product;
import com.amazonclone.backend.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    // Health check
    @GetMapping("/ping")
    public String ping() {
        return "Backend is working!";
    }

    // Get all with pagination + sorting
    @GetMapping
    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        Product saved = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody Product product) {
        return productRepository.findById(id)
                .map(existing -> {
                    existing.setName(product.getName());
                    existing.setDescription(product.getDescription());
                    existing.setPrice(product.getPrice());
                    existing.setQuantity(product.getQuantity());
                    existing.setCategory(product.getCategory());
                    existing.setImageUrl(product.getImageUrl());
                    Product updated = productRepository.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //  Search endpoint
    @GetMapping("/search")
    public Page<Product> search(@RequestParam String keyword, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword, pageable);
    }

    // Category filter
    @GetMapping("/category")
    public Page<Product> getByCategory(@RequestParam String category, Pageable pageable) {
        return productRepository.findByCategoryIgnoreCase(category, pageable);
    }
}
