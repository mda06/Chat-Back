package com.chat.chat.controller;

import com.chat.chat.dto.ChatParticipantDto;
import com.chat.chat.model.ChatParticipant;
import com.chat.chat.repository.ChatParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class WebSocketController {
    private final SimpMessagingTemplate template;
    private final ChatParticipantRepository chatParticipantRepository;

    @Autowired
    public WebSocketController(SimpMessagingTemplate template, ChatParticipantRepository repository) {
        this.template = template;
        this.chatParticipantRepository = repository;
    }

    @SubscribeMapping("/participants")
    public List<ChatParticipantDto> retrieveParticipants(SimpMessageHeaderAccessor headerAccessor) {
        return chatParticipantRepository.findAll().stream()
                .map(ChatParticipantDto::new)
                .collect(Collectors.toList());
    }


    @MessageMapping("/send/message")
    public void onReceiveMessage(String message) {
        template.convertAndSend("/chat", message);
    }
}
