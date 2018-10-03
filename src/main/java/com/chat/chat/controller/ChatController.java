package com.chat.chat.controller;

import com.chat.chat.dto.ChatMessageDto;
import com.chat.chat.model.ChatMessage;
import com.chat.chat.repository.ChatMessageRepository;
import com.chat.chat.repository.ChatParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import java.time.Instant;

@Controller
public class ChatController {
    private final SimpMessagingTemplate template;
    private final ChatParticipantRepository chatParticipantRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public ChatController(SimpMessagingTemplate template,
                          ChatParticipantRepository chatParticipantRepository,
                          ChatMessageRepository chatMessageRepository) {
        this.template = template;
        this.chatParticipantRepository = chatParticipantRepository;
        this.chatMessageRepository = chatMessageRepository;
    }

    @MessageMapping("/send/message")
    public void onReceiveMessage(String message, SimpMessageHeaderAccessor headerAccessor) {
        chatParticipantRepository.findBySessionId(headerAccessor.getSessionId())
                .ifPresent(chatParticipant -> {
                    ChatMessage msg = new ChatMessage(message, chatParticipant.getUsername(), Timestamp.from(Instant.now()).getTime());
                    chatMessageRepository.save(msg);
                    template.convertAndSend("/chat", new ChatMessageDto(msg));
        });
    }
}
