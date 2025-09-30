package dev.java.demo.dto;

import dev.java.demo.model.FoodItem;
import org.springframework.stereotype.Component;

@Component
public class FoodItemMapper {
public FoodItem map(FoodItemDTO foodItemDTO){
    FoodItem foodItem = new FoodItem();
    foodItem.setId(foodItemDTO.getId());
    foodItem.setName(foodItemDTO.getName());
    foodItem.setQuantity(foodItemDTO.getQuantity());
    foodItem.setExpirationDate(foodItemDTO.getExpirationDate());
    return foodItem;
}
public FoodItemDTO map(FoodItem foodItem){
    FoodItemDTO foodItemDTO = new FoodItemDTO();
    foodItemDTO.setId(foodItem.getId());
    foodItemDTO.setName(foodItem.getName());
    foodItemDTO.setQuantity(foodItem.getQuantity());
    foodItemDTO.setExpirationDate(foodItem.getExpirationDate());
    return foodItemDTO;
}

}
