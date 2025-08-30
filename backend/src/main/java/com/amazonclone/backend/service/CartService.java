package com.amazonclone.backend.service;

import com.amazonclone.backend.dto.CartItemDTO;
import com.amazonclone.backend.model.CartItem;
import com.amazonclone.backend.model.Product;
import com.amazonclone.backend.model.User;
import com.amazonclone.backend.repository.CartItemRepository;
import com.amazonclone.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public List<CartItemDTO> getCart(User user) {
        return cartItemRepository.findByUser(user).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CartItemDTO addToCart(Long productId, int quantity, User user) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> existingItem = cartItemRepository.findByProductIdAndUser(productId, user);

        CartItem cartItem;
        if (existingItem.isPresent()) {
            cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUser(user);
        }
        return convertToDto(cartItemRepository.save(cartItem));
    }

    public void removeFromCart(Long cartItemId) {
        // Optional: You could add a check here to ensure the item belongs to the user.
        cartItemRepository.deleteById(cartItemId);
    }

    public void clearCart(User user) {
        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        cartItemRepository.deleteAll(cartItems);
    }

    public List<CartItem> getCartEntities(User user) {
        return cartItemRepository.findByUser(user);
    }

    private CartItemDTO convertToDto(CartItem item) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(item.getId());
        dto.setQuantity(item.getQuantity());
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setPrice(item.getProduct().getPrice());
        return dto;
    }
}