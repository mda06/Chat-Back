package com.chat.chat.configuration;

import com.chat.chat.dto.ChatParticipantDto;
import com.chat.chat.model.ChatParticipant;
import com.chat.chat.repository.ChatParticipantRepository;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Optional;

public class PresenceEventListener {

    private String loginDestination = "/chat/login";
    private String logoutDestination = "/chat/logout";
    private SimpMessagingTemplate messagingTemplate;
    private ChatParticipantRepository repository;

    public PresenceEventListener(SimpMessagingTemplate msg,
                                 ChatParticipantRepository repo) {
        messagingTemplate = msg;
        repository = repo;
    }

    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
        String sessionId = SimpMessageHeaderAccessor.wrap(event.getMessage()).getSessionId();

        ChatParticipant chatParticipant = repository.findBySessionId(sessionId);
        if(chatParticipant == null)
            chatParticipant = new ChatParticipant(sessionId);
        else
            chatParticipant.setSessionId(sessionId);

        repository.save(chatParticipant);
        messagingTemplate.convertAndSend(loginDestination, new ChatParticipantDto(chatParticipant));
    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {
        Optional.ofNullable(repository.findBySessionId(event.getSessionId()))
                .ifPresent(chatParticipant -> {
                    messagingTemplate.convertAndSend(logoutDestination, chatParticipant);
                    chatParticipant.setSessionId(null);
                    repository.save(chatParticipant);
                });
    }
}
