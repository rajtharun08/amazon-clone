package com.amazonclone.backend.repository;

import com.amazonclone.backend.model.CartItem;
import com.amazonclone.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByProductIdAndUser(Long productId, User user); //  UPDATED METHOD

    List<CartItem> findByUser(User user); 
}