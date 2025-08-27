package com.amazonclone.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.amazonclone.backend.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> { }
