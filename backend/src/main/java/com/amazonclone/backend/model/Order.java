package com.amazonclone.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders") // 'order' is reserved in SQL
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;
    private BigDecimal totalAmount;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<CartItem> items;
}
