package com.amazonclone.backend.repository;
import com.amazonclone.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.amazonclone.backend.model.Order;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
    List<Order> findByUser(User user); // added find by user
}