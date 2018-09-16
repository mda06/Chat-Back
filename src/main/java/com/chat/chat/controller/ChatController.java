package com.chat.chat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("chat")
public class ChatController {
    @GetMapping
    public String getTest() {
        return "Hello chat application !";
    }
}
