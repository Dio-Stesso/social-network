package com.benatti.socialnetwork.service.mapper;

import com.benatti.socialnetwork.model.ChatMessage;
import com.benatti.socialnetwork.model.dto.MessageResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    public MessageResponseDto mapToDto(ChatMessage message) {
        MessageResponseDto responseDto = new MessageResponseDto();
        responseDto.setId(message.getId());
        responseDto.setMessage(message.getMessage());
        responseDto.setSendDate(message.getSendDate());
        responseDto.setSenderName(message.getSender().getUsername());
        return responseDto;
    }
}
