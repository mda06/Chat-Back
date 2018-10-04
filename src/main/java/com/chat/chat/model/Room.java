package com.chat.chat.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Room {
    @Id @GeneratedValue
    private long id;
    private String name;
    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<ChatMessage> messages;

    public Room() {
    }

    public Room(long id, String name, List<ChatMessage> messages) {
        this.id = id;
        this.name = name;
        this.messages = messages;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }
}
