package com.amazonclone.backend.service;

import com.amazonclone.backend.model.Product;
import com.amazonclone.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return productRepository.existsById(id);
    }

    public Page<Product> search(String keyword, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword, pageable);
    }

    public Page<Product> getByCategory(String category, Pageable pageable) {
        return productRepository.findByCategoryIgnoreCase(category, pageable);
    }
    // we set active = false
    public void softDelete(Long id) {
        productRepository.findById(id).ifPresent(product -> {
            product.setActive(false);
            productRepository.save(product);
        });
    }
}
