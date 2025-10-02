package dev.java.demo.controller;

import dev.java.demo.service.ChatGptService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RecipeControler {
    ChatGptService chatGptService;

    public RecipeControler(ChatGptService chatGptService){
        this.chatGptService = chatGptService;
    }
    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateRecipe(){
        return chatGptService.generateRecipe()
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }



}


