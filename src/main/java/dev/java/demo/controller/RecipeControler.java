package dev.java.demo.controller;

import dev.java.demo.service.ChatGptService;
import dev.java.demo.service.FoodItemService;
import dev.java.demo.service.GeminiService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RecipeControler {
    //ChatGptService chatGptService;
    GeminiService geminiService;
    FoodItemService foodItemService;

    public RecipeControler(ChatGptService chatGptService, GeminiService geminiService, FoodItemService foodItemService){
        this.geminiService = geminiService;
        this.foodItemService = foodItemService;

    }
//    @GetMapping("/generate")
//    public Mono<ResponseEntity<String>> generateRecipe(){
//        return chatGptService.generateRecipe()
//                .map(recipe -> ResponseEntity.ok(recipe))
//                .defaultIfEmpty(ResponseEntity.noContent().build());
//    }
    @GetMapping("/gemini")
    public Mono<ResponseEntity<String>> generateRecipeGemini(){

        return geminiService.generateRecipe(foodItemService.showAll())
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}


