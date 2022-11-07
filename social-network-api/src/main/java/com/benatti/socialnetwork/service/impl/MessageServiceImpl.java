package com.benatti.socialnetwork.service.impl;

import com.benatti.socialnetwork.model.ChatMessage;
import com.benatti.socialnetwork.model.ChatRoom;
import com.benatti.socialnetwork.repository.ChatMessageRepository;
import com.benatti.socialnetwork.repository.ChatRoomRepository;
import com.benatti.socialnetwork.service.MessageService;
import com.benatti.socialnetwork.service.UserService;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    private final ChatMessageRepository messageRepository;
    private final ChatRoomRepository roomRepository;
    private final UserService userService;

    public MessageServiceImpl(ChatMessageRepository messageRepository,
                              ChatRoomRepository roomRepository,
                              UserService userService) {
        this.messageRepository = messageRepository;
        this.roomRepository = roomRepository;
        this.userService = userService;
    }

    @Override
    public List<ChatMessage> findAllByRoom(Long roomId) {
        List<ChatMessage> chatMessages = messageRepository.findAllByRoom(
                roomRepository.findById(roomId).orElseThrow(() ->
                        new RuntimeException("Room not founded, id " + roomId)));
        return chatMessages.stream()
                .sorted(Comparator.comparing(ChatMessage::getSendDate))
                .collect(Collectors.toList());
    }

    @Override
    public ChatMessage save(String messageText, String senderName, Long roomId) {
        Optional<ChatRoom> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isPresent()
                && (roomOptional.get().getFirstUser().equals(senderName)
                || roomOptional.get().getSecondUser().equals(senderName))) {
            ChatMessage message = new ChatMessage();
            message.setMessage(messageText);
            message.setRoom(roomOptional.get());
            message.setSender(userService
                    .findByUsernameOrEmail(senderName, "")
                    .orElseThrow(() -> new RuntimeException(
                            "Can't find the user by name: " + senderName)));
            message.setSendDate(LocalDateTime.now());
            return messageRepository.save(message);
        }
        throw new RuntimeException("Can`t send message by properties: "
                + senderName + " " + roomId);
    }

    @Override
    public void deleteById(Long messageId) {
        messageRepository.deleteById(messageId);
    }
}
