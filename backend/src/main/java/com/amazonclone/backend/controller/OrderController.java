package com.amazonclone.backend.controller;

import com.amazonclone.backend.model.Order;
import com.amazonclone.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder() {
        return ResponseEntity.ok(orderService.placeOrder());
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getOrders();
    }
}
