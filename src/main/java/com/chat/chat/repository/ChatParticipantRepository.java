package com.chat.chat.repository;

import com.chat.chat.model.ChatParticipant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatParticipantRepository extends CrudRepository<ChatParticipant, Long> {
    @Override
    List<ChatParticipant> findAll();
    ChatParticipant findBySessionId(String sessionId);
}