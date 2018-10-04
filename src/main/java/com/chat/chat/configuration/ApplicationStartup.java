package com.chat.chat.configuration;

import com.chat.chat.model.Room;
import com.chat.chat.repository.ChatParticipantRepository;
import com.chat.chat.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent>{
    private final ChatParticipantRepository repository;
    private final RoomRepository roomRepository;

    @Autowired
    public ApplicationStartup(ChatParticipantRepository repository,
                              RoomRepository roomRepository) {
        this.repository = repository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        //If the API shut down before the previous websockets are closed, the records are not deleted
        //So, delete all previous data before running the API
        repository.deleteAll();
        //Mock some data for the rooms
        roomRepository.save(new Room(0, "General", Collections.emptyList()));
        roomRepository.save(new Room(0, "Humour", Collections.emptyList()));
        roomRepository.save(new Room(0, "Eating", Collections.emptyList()));
    }
}