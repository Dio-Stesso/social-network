package com.benatti.socialnetwork.controller;

import com.benatti.socialnetwork.model.dto.MessageResponseDto;
import com.benatti.socialnetwork.service.MessageService;
import com.benatti.socialnetwork.service.mapper.MessageMapper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chats/messages")
@CrossOrigin("*")
public class MessageController {
    private final MessageService messageService;
    private final MessageMapper messageMapper;

    public MessageController(MessageService messageService, MessageMapper messageMapper) {
        this.messageService = messageService;
        this.messageMapper = messageMapper;
    }

    @PostMapping("/{roomId}")
    public MessageResponseDto sendMessage(@PathVariable Long roomId,
                                          @RequestBody String messageText,
                                          Authentication authentication) {
        return messageMapper.mapToDto(messageService
                .save(messageText, authentication.getName(), roomId));
    }

    @DeleteMapping("/{messageId}")
    public void deleteMessage(@PathVariable Long messageId) {
        messageService.deleteById(messageId);
    }
}
