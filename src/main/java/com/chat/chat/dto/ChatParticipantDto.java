package com.chat.chat.dto;

import com.chat.chat.model.ChatParticipant;

public class ChatParticipantDto {
    private String sessionId;

    public ChatParticipantDto() {    }

    public ChatParticipantDto(String sessionId) {
        this.sessionId = sessionId;
    }

    public ChatParticipantDto(ChatParticipant chatParticipant) {
        this.sessionId = chatParticipant.getSessionId();
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
