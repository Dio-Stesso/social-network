package com.benatti.socialnetwork.model.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MessageResponseDto {
    private Long id;
    private String senderName;
    private LocalDateTime sendDate;
    private String message;
}
