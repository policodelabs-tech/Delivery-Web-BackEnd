package com.example.demo.controller;

import com.microsoft.bot.builder.Bot;
import com.microsoft.bot.integration.BotFrameworkHttpAdapter;
import com.microsoft.bot.integration.spring.BotController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/messages")
public class DeliveryBotController extends BotController {

    @Autowired
    public DeliveryBotController(BotFrameworkHttpAdapter adapter, Bot bot) {
        super(adapter, bot);
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<Object>> processMessage(
            @RequestBody String body,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        return super.invoke(body, authHeader);
    }
}