package com.benatti.socialnetwork.service;

import com.benatti.socialnetwork.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);

    User findById(Long id);

    Optional<User> findByUsernameOrEmail(String username, String email);

    void delete(Long id);

    List<User> findAll();

    User findByResetPasswordId(String id);

    User updatePassword(User user, String newPassword);
}
