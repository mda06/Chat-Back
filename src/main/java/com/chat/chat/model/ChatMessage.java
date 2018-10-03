package com.chat.chat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ChatMessage {
    @Id @GeneratedValue
    private long id;
    private String message;
    private String username;
    private long timestamp;

    public ChatMessage(String message, String username, long timestamp) {
        this.message = message;
        this.username = username;
        this.timestamp = timestamp;
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
