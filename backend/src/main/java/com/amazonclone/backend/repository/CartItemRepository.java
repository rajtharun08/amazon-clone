package com.amazonclone.backend.repository;

import com.amazonclone.backend.model.CartItem;
//import com.amazonclone.backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> { }


