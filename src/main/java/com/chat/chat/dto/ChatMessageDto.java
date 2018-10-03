package com.chat.chat.dto;

import com.chat.chat.model.ChatMessage;

public class ChatMessageDto {
    private String message;
    private String username;
    private long timestamp;

    public ChatMessageDto() {
    }

    public ChatMessageDto(String message, String username, long timestamp) {
        this.message = message;
        this.username = username;
        this.timestamp = timestamp;
    }

    public ChatMessageDto(ChatMessage msg) {
        this.message = msg.getMessage();
        this.username = msg.getUsername();
        this.timestamp = msg.getTimestamp();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
