package com.benatti.socialnetwork.service.mapper;

import com.benatti.socialnetwork.model.dto.RoomResponseDto;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {
    public RoomResponseDto toDto(String username, String lastMessage, Long id) {
        RoomResponseDto responseDto = new RoomResponseDto();
        responseDto.setId(id);
        responseDto.setName(username);
        responseDto.setLastMessage(lastMessage);
        return responseDto;
    }
}
