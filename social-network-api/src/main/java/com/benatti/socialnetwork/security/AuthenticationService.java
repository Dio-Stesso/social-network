package com.benatti.socialnetwork.security;

import com.benatti.socialnetwork.exception.AuthenticationException;
import com.benatti.socialnetwork.model.User;

public interface AuthenticationService {
    User register(User user) throws AuthenticationException;

    User login(String login, String password) throws AuthenticationException;
}
