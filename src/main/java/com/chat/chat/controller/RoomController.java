package com.chat.chat.controller;

import com.chat.chat.dto.ChatMessageDto;
import com.chat.chat.dto.RoomDto;
import com.chat.chat.model.ChatMessage;
import com.chat.chat.model.ChatParticipant;
import com.chat.chat.model.Room;
import com.chat.chat.repository.ChatMessageRepository;
import com.chat.chat.repository.ChatParticipantRepository;
import com.chat.chat.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class RoomController {
    private final SimpMessagingTemplate template;
    private final RoomRepository roomRepository;
    private final ChatParticipantRepository chatParticipantRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public RoomController(SimpMessagingTemplate template,
                          RoomRepository roomRepository,
                          ChatParticipantRepository chatParticipantRepository,
                          ChatMessageRepository chatMessageRepository) {
        this.template = template;
        this.roomRepository = roomRepository;
        this.chatParticipantRepository = chatParticipantRepository;
        this.chatMessageRepository = chatMessageRepository;
    }

    @SubscribeMapping("/room/all")
    public List<RoomDto> retrieveRooms() {
        return roomRepository.findAll().stream()
                .map(RoomDto::new)
                .map(room -> {
                    room.setMessages(room.getMessages().stream().limit(10).collect(Collectors.toList()));
                    return room;
                })
                .collect(Collectors.toList());
    }

    @MessageMapping("/room/send/{roomId}")
    public void onReceiveMessage(String message, @DestinationVariable("roomId") long roomId, SimpMessageHeaderAccessor headerAccessor) {
        Optional<Room> optRoom = roomRepository.findById(roomId);
        Optional<ChatParticipant> optParticipant = chatParticipantRepository.findBySessionId(headerAccessor.getSessionId());
        if(optRoom.isPresent() && optParticipant.isPresent()) {
            Room room = optRoom.get();
            ChatParticipant participant = optParticipant.get();
            ChatMessage msg = new ChatMessage(message, participant.getUsername(), Timestamp.from(Instant.now()).getTime());
            room.getMessages().add(msg);
            chatMessageRepository.save(msg);
            roomRepository.save(room);
            template.convertAndSend("/room/receive/" + roomId, new ChatMessageDto(msg));
        }
    }
}
