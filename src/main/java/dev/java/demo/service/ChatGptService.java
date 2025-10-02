package dev.java.demo.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class ChatGptService {

    private final WebClient webClient;
    private String apiKey = System.getenv("API_KEY");
    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }

/*
* curl https://api.openai.com/v1/responses \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $OPENAI_API_KEY" \
  -d '{
    "model": "gpt-5",
    "input": "Write a short bedtime story about a unicorn."
  }'
*
* */
    public Mono<String> generateRecipe(){
        String prompt = "which country rio de janeiro is located ";
        Map <String, Object> requestBody = Map.of(
                "model","gpt-4o-mini",
                "input",prompt
        );
        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {var choices = (List<Map<String, Object>>) response.get("choices");
                    if (choices != null && !choices.isEmpty()) {
                        var firstChoice = choices.get(0);
                        // Depending on API response structure, it may be "text" or nested under "message"
                        if (firstChoice.containsKey("text")) {
                            return firstChoice.get("text").toString();
                        } else if (firstChoice.containsKey("message")) {
                            Map<String, Object> message = (Map<String, Object>) firstChoice.get("message");
                            return message.get("content").toString();
                        }
                    }
                    return "No response found";
                });


    }

}
