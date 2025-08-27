package com.amazonclone.backend.service;

import com.amazonclone.backend.model.CartItem;
import com.amazonclone.backend.model.Order;
import com.amazonclone.backend.repository.CartItemRepository;
import com.amazonclone.backend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;

    public Order placeOrder() {
        List<CartItem> cartItems = cartItemRepository.findAll();

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty!");
        }

        BigDecimal total = cartItems.stream()
                .map(item -> item.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = Order.builder()
                .orderDate(LocalDateTime.now())
                .totalAmount(total)
                .items(cartItems)
                .build();

        // Save order & clear cart
        Order saved = orderRepository.save(order);
        cartItemRepository.deleteAll();

        return saved;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
