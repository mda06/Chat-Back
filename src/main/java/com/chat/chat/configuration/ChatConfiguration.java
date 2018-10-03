package com.chat.chat.configuration;

import com.chat.chat.repository.ChatParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configuration
public class ChatConfiguration {
    private final ChatParticipantRepository repository;

    @Autowired
    public ChatConfiguration(ChatParticipantRepository repository) {
        this.repository = repository;
    }

    @Bean
    public PresenceEventListener presenceEventListener(SimpMessagingTemplate messagingTemplate) {
        return new PresenceEventListener(messagingTemplate, repository);
    }
}
