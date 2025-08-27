package com.amazonclone.backend.service;

import com.amazonclone.backend.model.CartItem;
import com.amazonclone.backend.model.Product;
import com.amazonclone.backend.repository.CartItemRepository;
import com.amazonclone.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public List<CartItem> getCart() {
        return cartItemRepository.findAll();
    }

    public CartItem addToCart(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem item = CartItem.builder()
                .product(product)
                .quantity(quantity)
                .build();

        return cartItemRepository.save(item);
    }

    public void removeFromCart(Long id) {
        cartItemRepository.deleteById(id);
    }

    public void clearCart() {
        cartItemRepository.deleteAll();
    }
}

