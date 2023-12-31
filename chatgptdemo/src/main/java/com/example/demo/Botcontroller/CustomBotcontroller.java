package com.example.demo.Botcontroller;

import com.example.demo.dto.ChatGptRequest;
import com.example.demo.dto.ChatGptResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bot")
public class CustomBotcontroller {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;

    @GetMapping("/chat")
    public ChatGptResponse chat(@RequestParam("prompt")String prompt){

        ChatGptRequest request = new ChatGptRequest(model,prompt);
        ChatGptResponse response = template.postForObject(apiURL,request,ChatGptResponse.class);

        return response;
    }
}
