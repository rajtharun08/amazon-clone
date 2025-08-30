package com.amazonclone.backend.controller;

import com.amazonclone.backend.dto.OrderDTO;
import com.amazonclone.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<OrderDTO> placeOrder(Authentication authentication) {
        String userEmail = authentication.getName();
        return ResponseEntity.ok(orderService.placeOrder(userEmail));
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrders(Authentication authentication) {
        String userEmail = authentication.getName();
        return ResponseEntity.ok(orderService.getOrders(userEmail));
    }
}