package com.amazonclone.backend.model;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
    private String category;
    private String imageUrl;
	public boolean isOutOfStock() {
    return quantity <= 0;
    }

}
