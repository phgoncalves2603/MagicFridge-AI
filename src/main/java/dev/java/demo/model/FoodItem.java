package dev.java.demo.model;

import dev.java.demo.enums.foodCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="Food_item")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private foodCategory category;
    private LocalDate expirationDate;

}
