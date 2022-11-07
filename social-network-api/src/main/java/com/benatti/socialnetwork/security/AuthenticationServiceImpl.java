package com.benatti.socialnetwork.security;

import com.benatti.socialnetwork.exception.AuthenticationException;
import com.benatti.socialnetwork.model.User;
import com.benatti.socialnetwork.service.RoleService;
import com.benatti.socialnetwork.service.UserService;
import java.util.Optional;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(UserService userService,
                                     RoleService roleService,
                                     PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) throws AuthenticationException {
        if (userService.findByUsernameOrEmail(user.getUsername(), user.getEmail()).isPresent()) {
            throw new AuthenticationException("Email or username already exist!");
        }
        user.setRoles(Set.of(roleService.getRoleByName("USER")));
        return userService.save(user);
    }

    @Override
    public User login(String username, String password) throws AuthenticationException {
        Optional<User> user = userService.findByUsernameOrEmail(username, "");
        if (user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())) {
            throw new AuthenticationException("Incorrect username or password!!!");
        }
        return user.get();
    }
}
