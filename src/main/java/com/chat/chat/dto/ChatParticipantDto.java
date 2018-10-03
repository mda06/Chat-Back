package com.chat.chat.dto;

import com.chat.chat.model.ChatParticipant;

public class ChatParticipantDto {
    private String sessionId;
    private String username;

    public ChatParticipantDto() {    }

    public ChatParticipantDto(ChatParticipant chatParticipant) {
        this.sessionId = chatParticipant.getSessionId();
        this.username = chatParticipant.getUsername();
    }

    public void updateModel(ChatParticipant model) {
        model.setSessionId(sessionId);
        model.setUsername(username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
