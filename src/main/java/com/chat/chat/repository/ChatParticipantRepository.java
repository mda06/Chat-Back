package com.chat.chat.repository;

import com.chat.chat.model.ChatParticipant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatParticipantRepository extends CrudRepository<ChatParticipant, Long> {
    @Override
    List<ChatParticipant> findAll();
    Optional<ChatParticipant> findBySessionId(String sessionId);
}