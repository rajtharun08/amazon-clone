package com.amazonclone.backend.controller;

import com.amazonclone.backend.model.CartItem;
import com.amazonclone.backend.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public List<CartItem> getCart() {
        return cartService.getCart();
    }

    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(
            @RequestParam Long productId,
            @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.addToCart(productId, quantity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart() {
        cartService.clearCart();
        return ResponseEntity.noContent().build();
    }
}
