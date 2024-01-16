package org.ssafy.pasila.global.gpt3.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.ssafy.pasila.global.gpt3.model.ChatRequest;
import org.ssafy.pasila.global.gpt3.model.ChatResponse;


@RestController
@Slf4j
public class ChatController {

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.api.id}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @GetMapping("/chat")
    public String chat(@RequestParam String prompt) {

        log.info("prompt: {}", prompt);
        String system = "파챗은 급식체라고 불리며 초등학생, 유치한 사람입니다.";
//        String system = "파챗은 나선욱체라고 불리며 허세넘치는 사람입니다.";
        ChatRequest request = new ChatRequest(model, system ,prompt);
        log.info("chat request: {}",request.getModel());
        // call the API
        try {
            ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);
            assert response != null;

            if (response.getChoices() == null || response.getChoices().isEmpty()) {
                return "No response";
            }

            // return the first response
            return response.getChoices().get(0).getMessage().getContent();

        } catch (RestClientException e) {
            log.error("Error occurred while making the API request: {}", e.getMessage());
        }

        return prompt;
    }


}