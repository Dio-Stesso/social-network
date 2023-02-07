package com.benatti.socialnetwork.controller;

import com.benatti.socialnetwork.model.ChatRoom;
import com.benatti.socialnetwork.model.User;
import com.benatti.socialnetwork.model.dto.RoomResponseDto;
import com.benatti.socialnetwork.model.dto.UserResponseDto;
import com.benatti.socialnetwork.service.MailSender;
import com.benatti.socialnetwork.service.RoomService;
import com.benatti.socialnetwork.service.UserService;
import com.benatti.socialnetwork.service.mapper.RoomMapper;
import com.benatti.socialnetwork.service.mapper.UserMapper;
import com.benatti.socialnetwork.validation.Email;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final RoomService roomService;
    private final RoomMapper roomMapper;
    private final MailSender mailSender;

    public UserController(UserService userService,
                          UserMapper userMapper,
                          RoomService roomService,
                          RoomMapper roomMapper,
                          MailSender mailSender) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.roomService = roomService;
        this.roomMapper = roomMapper;
        this.mailSender = mailSender;
    }

    @GetMapping
    public List<UserResponseDto> getAll(Authentication authentication) {
        return userService.findAll()
                .stream()
                .filter(user -> !user.getUsername().equals(authentication.getName()))
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public RoomResponseDto createRoom(@RequestBody String username,
                                      Authentication authentication) {
        if (userService.findByUsernameOrEmail(username, "").isPresent()
                && !username.equals(authentication.getName())) {
            ChatRoom room = roomService.createRoom(username, authentication.getName());
            return roomMapper.toDto(username, "", room.getId());
        }
        throw new RuntimeException("Can`t create room by username " + username);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Object> sendIdForResetPassword(@RequestBody @Email @Valid String email) {
        Optional<User> optionalUser = userService.findByUsernameOrEmail("", email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getResetPasswordId() == null) {
                user.setResetPasswordId(UUID.randomUUID().toString());
                userService.save(user);
            }
            mailSender.send(email,
                    "Reset password",
                    String.format("Reset password id "
                            + user.getResetPasswordId()));
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/reset-password/{uuid}")
    public ResponseEntity<Object> resetPassword(@PathVariable String uuid) {
        try {
            userService.findByResetPasswordId(uuid);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/reset-password/{uuid}")
    public ResponseEntity<Object> resetPassword(@RequestBody String password,
                                                @PathVariable String uuid) {
        try {
            userService.updatePassword(userService.findByResetPasswordId(uuid), password);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleException(RuntimeException ex) {
        return new ResponseEntity<>(Map.of("errorMessage",
                ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
