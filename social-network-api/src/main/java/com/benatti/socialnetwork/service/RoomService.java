package com.benatti.socialnetwork.service;

import com.benatti.socialnetwork.model.ChatRoom;
import java.util.List;

public interface RoomService {
    ChatRoom find(Long id);

    ChatRoom save(ChatRoom room);

    void delete(ChatRoom room);

    List<ChatRoom> findByUsername(String username);

    ChatRoom createRoom(String firstUser, String secondUser);
}
