package com.chat.chat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ChatParticipant {
    @Id @GeneratedValue
    private long id;
    private String sessionId;
    private String username;

    public ChatParticipant() {
    }

    public ChatParticipant(String sessionId, String username) {
        this.sessionId = sessionId;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
