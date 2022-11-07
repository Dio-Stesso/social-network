package com.benatti.socialnetwork.repository;

import com.benatti.socialnetwork.model.ChatMessage;
import com.benatti.socialnetwork.model.ChatRoom;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findAllByRoom(ChatRoom chatRoom);

    void deleteById(Long messageId);
}
