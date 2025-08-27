package com.amazonclone.backend.model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    private BigDecimal price;
    private int stock;

   // private boolean deleted = false; // for soft delete
    // For now, we assume single user. Later we can add User entity.
}
