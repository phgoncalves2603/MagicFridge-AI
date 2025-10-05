package dev.java.demo.service;

import dev.java.demo.dto.FoodItemDTO;
import dev.java.demo.model.FoodItem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GeminiService {

    private final WebClient webClient;
    private final String geminiApiKey = System.getenv("geminiApiKey");

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://generativelanguage.googleapis.com/v1beta/models").build();
    }

    public Mono<String> generateRecipe(List<FoodItemDTO> foodItems) {
        String food = foodItems.stream()
                .map(item -> String.format("%s (%s) - Quantity: %d, experation date: %s",
                        item.getName(), item.getCategory(), item.getQuantity(), item.getExpirationDate()))
                .collect(Collectors.joining("\n"));

        String prompt = "based on my database create a recipe using those items: "+food;

        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of(
                                "parts", List.of(
                                        Map.of("text", prompt)
                                )
                        )
                )
        );

        return webClient.post()
                .uri("/gemini-2.5-flash:generateContent")
                .header("x-goog-api-key", geminiApiKey)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class);
    }
}
