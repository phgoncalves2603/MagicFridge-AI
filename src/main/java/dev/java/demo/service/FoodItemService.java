package dev.java.demo.service;

import dev.java.demo.dto.FoodItemDTO;
import dev.java.demo.dto.FoodItemMapper;
import dev.java.demo.model.FoodItem;
import dev.java.demo.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodItemService {
    FoodItemRepository repository;
    FoodItemMapper mapper;
    public FoodItemService( FoodItemRepository repository,FoodItemMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }
    //GET
    public List<FoodItemDTO> showAll(){
        List<FoodItem> foodItems = repository.findAll();
        return foodItems.stream()//transform to a stream
                .map(mapper::map)//convert each foodItem to foodItemDto
                .collect(Collectors.toList());//store all in a list
    }
    public FoodItemDTO showById(Long id){
        Optional<FoodItem> foodItem = repository.findById(id);
        return foodItem.map(mapper::map).orElse(null);
    }
    //POST
    public void Create(FoodItemDTO foodItemDTO){
        FoodItem foodItem = mapper.map(foodItemDTO);
        repository.save(foodItem);
    }

    //Delete
    public void delete(Long id){
        Optional<FoodItem> foodItem = repository.findById(id);
        if (foodItem.isPresent()){
            repository.deleteById(id);
        }

    }
    //Update

    public FoodItemDTO update(Long id, FoodItemDTO foodItemDTO){
        Optional<FoodItem> foodItem = repository.findById(id);
        if (foodItem.isPresent()){
            FoodItem newFoodItem = mapper.map(foodItemDTO);
            newFoodItem.setId(id);
            repository.save(newFoodItem);
            return mapper.map(newFoodItem);
        }
        return null;
    }



}
