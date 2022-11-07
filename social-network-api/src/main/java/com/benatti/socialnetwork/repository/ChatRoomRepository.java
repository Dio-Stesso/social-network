package com.benatti.socialnetwork.repository;

import com.benatti.socialnetwork.model.ChatRoom;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    @Query("FROM ChatRoom r WHERE r.firstUser=?1 OR r.secondUser=?1")
    List<ChatRoom> findByUser(String username);

    @Query("FROM ChatRoom r WHERE r.firstUser=?1 AND r.secondUser=?2 "
            + "OR r.firstUser=?2 AND r.secondUser=?1")
    Optional<ChatRoom> findByFirstUserAndSecondUser(String firstUser, String secondUser);
}
