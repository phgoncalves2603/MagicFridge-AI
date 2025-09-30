package dev.java.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Id()
    private Long id;
    private String name;
    private Integer quantity;
    private LocalDate expirationDate;

}
