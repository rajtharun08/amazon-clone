package com.amazonclone.backend.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.amazonclone.backend.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Search by name or description containing a keyword
    Page<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
        String name, String description, Pageable pageable);

    // Filter by category
    Page<Product> findByCategoryIgnoreCase(String category, Pageable pageable);
}