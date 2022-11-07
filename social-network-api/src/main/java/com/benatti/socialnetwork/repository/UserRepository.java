package com.benatti.socialnetwork.repository;

import com.benatti.socialnetwork.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("FROM User u JOIN FETCH u.roles WHERE u.username=?1 OR u.email=?2")
    Optional<User> findUserByUsernameOrEmail(String username, String email);

    @Query("FROM User u JOIN FETCH u.roles WHERE u.id = ?1")
    Optional<User> find(Long id);

    Optional<User> findByResetPasswordId(String id);
}
