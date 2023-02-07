package com.benatti.socialnetwork.repository;

import com.benatti.socialnetwork.model.ChatRoom;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    @Query("FROM ChatRoom r WHERE r.firstUserUsername=?1 OR r.secondUserUsername=?1")
    List<ChatRoom> findByUser(String username);

    @Query("FROM ChatRoom r WHERE r.firstUserUsername=?1 AND r.secondUserUsername=?2 "
            + "OR r.firstUserUsername=?2 AND r.secondUserUsername=?1")
    Optional<ChatRoom> findByFirstUserUsernameAndSecondUserUsernameIn(
            String firstUserUsername, String secondUserUsername);
}
