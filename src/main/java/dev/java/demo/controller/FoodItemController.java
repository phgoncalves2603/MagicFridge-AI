package dev.java.demo.controller;

import dev.java.demo.dto.FoodItemDTO;
import dev.java.demo.model.FoodItem;
import dev.java.demo.repository.FoodItemRepository;
import dev.java.demo.service.FoodItemService;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/FoodItem")
public class FoodItemController {
    FoodItemService foodItemService;
    public FoodItemController(FoodItemService foodItemService){
        this.foodItemService = foodItemService;
    }
    //GET
    @GetMapping("/ShowAll")
    public ResponseEntity<List<FoodItemDTO>> showAllFoodItems(){
        List<FoodItemDTO> foodItemsDTO = foodItemService.showAll();
        return ResponseEntity.ok(foodItemsDTO);
    }
    @GetMapping("/showItem/{id}")
    public ResponseEntity<?> showItem(@PathVariable Long id){
        FoodItemDTO foodItemDTO= foodItemService.showById(id);
        if (foodItemDTO==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("FoodItem ID: "+id+" Not found");
        }
        return ResponseEntity.ok(foodItemDTO);
    }
    //POST
    @PostMapping("/addItem")
    public ResponseEntity<String> addFoodItem(@RequestBody FoodItemDTO foodItem){
        FoodItemDTO newFoodItemDTO = foodItemService.Create(foodItem);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Food Item: "+ foodItem.getName()+" Created");
    }
    //UPDATE
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateFoodItem(@PathVariable Long id, @RequestBody FoodItemDTO foodItemDTO){
        if(foodItemService.showById(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Food Item ID: "+id+" Not Found");
        }
        FoodItemDTO foodItem = foodItemService.update(id,foodItemDTO);
        return ResponseEntity.ok(foodItem);
    }
    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(foodItemService.showById(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Food Item ID: "+id+" Not Found");
        }
        foodItemService.delete(id);
        return ResponseEntity.ok("Food Item ID: "+id+" Successfully deleted");
    }

}
