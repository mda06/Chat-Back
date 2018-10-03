package com.chat.chat.controller;

import com.chat.chat.dto.ChatParticipantDto;
import com.chat.chat.repository.ChatParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
public class ChatParticipantController {
    private final SimpMessagingTemplate template;
    private final ChatParticipantRepository chatParticipantRepository;

    @Autowired
    public ChatParticipantController(SimpMessagingTemplate template, ChatParticipantRepository repository) {
        this.template = template;
        this.chatParticipantRepository = repository;
    }

    @SubscribeMapping("/participant/all")
    public List<ChatParticipantDto> retrieveParticipants() {
        return chatParticipantRepository.findAll().stream()
                .map(ChatParticipantDto::new)
                .collect(Collectors.toList());
    }

    @MessageMapping("/participant/update")
    public void onUpdateParticipant(ChatParticipantDto dto) {
        chatParticipantRepository.findBySessionId(dto.getSessionId()).ifPresent(chatParticipant -> {
            dto.updateModel(chatParticipant);
            chatParticipantRepository.save(chatParticipant);
        });
        template.convertAndSend("/participant/update", dto);
    }


}
