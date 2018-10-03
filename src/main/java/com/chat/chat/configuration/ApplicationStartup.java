package com.chat.chat.configuration;

import com.chat.chat.repository.ChatParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent>{
    private final ChatParticipantRepository repository;

    @Autowired
    public ApplicationStartup(ChatParticipantRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        //If the API shut down before the previous websockets are closed, the records are not deleted
        //So, delete all previous data before running the API
        repository.deleteAll();
    }
}