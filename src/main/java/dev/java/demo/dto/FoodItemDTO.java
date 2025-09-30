package dev.java.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class FoodItemDTO {
    private Long id;
    private String name;
    private Integer quantity;
    private LocalDate expirationDate;
}
