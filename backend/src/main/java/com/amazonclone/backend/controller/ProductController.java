package com.amazonclone.backend.controller;

import com.amazonclone.backend.model.Product;
import com.amazonclone.backend.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/ping")
    public String ping() {
        return "Backend is working!";
    }

    @GetMapping
    public Page<Product> getAll(Pageable pageable) {
        return productService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return productService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        Product saved = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody Product product) {
        return productService.getById(id)
                .map(existing -> {
                    existing.setName(product.getName());
                    existing.setDescription(product.getDescription());
                    existing.setPrice(product.getPrice());
                    existing.setQuantity(product.getQuantity());
                    existing.setCategory(product.getCategory());
                    existing.setImageUrl(product.getImageUrl());
                    Product updated = productService.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public Page<Product> search(@RequestParam String keyword, Pageable pageable) {
        return productService.search(keyword, pageable);
    }

    @GetMapping("/category")
    public Page<Product> getByCategory(@RequestParam String category, Pageable pageable) {
        return productService.getByCategory(category, pageable);
    }
}
