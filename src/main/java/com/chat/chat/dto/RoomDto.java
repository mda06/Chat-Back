package com.chat.chat.dto;

import com.chat.chat.model.Room;

import java.util.List;
import java.util.stream.Collectors;

public class RoomDto {
    private long id;
    private String name;
    private List<ChatMessageDto> messages;

    public RoomDto() {
    }

    public RoomDto(Room room) {
        id = room.getId();
        name = room.getName();
        messages = room.getMessages()
                .stream()
                .map(ChatMessageDto::new)
                .collect(Collectors.toList());
    }

    public RoomDto(long id, String name, List<ChatMessageDto> messages) {
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

    public List<ChatMessageDto> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessageDto> messages) {
        this.messages = messages;
    }
}
