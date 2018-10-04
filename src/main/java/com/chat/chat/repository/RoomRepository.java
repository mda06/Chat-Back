package com.chat.chat.repository;

import com.chat.chat.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
    @Override
    List<Room> findAll();
}
