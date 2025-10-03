package dev.java.demo.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    private final WebClient webClient;
    private final String geminiApiKey = System.getenv("geminiApiKey");

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://generativelanguage.googleapis.com/v1beta/models").build();
    }

    public Mono<String> generateRecipe() {
        String prompt = "Tell me who is the best Vasco da Gama player";

        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of(
                                "parts", List.of(
                                        Map.of("text", prompt) // ✅ use variable, not string literal
                                )
                        )
                )
        );

        return webClient.post()
                .uri("/gemini-2.5-flash:generateContent")
                .header("x-goog-api-key", geminiApiKey) // ✅ correct header usage
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class);
    }
}
