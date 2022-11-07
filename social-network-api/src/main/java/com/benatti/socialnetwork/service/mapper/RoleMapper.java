package com.benatti.socialnetwork.service.mapper;

import com.benatti.socialnetwork.model.Role;
import com.benatti.socialnetwork.model.dto.RoleResponseDto;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleResponseDto mapToDto(Role role) {
        RoleResponseDto responseDto = new RoleResponseDto();
        responseDto.setId(role.getId());
        responseDto.setName(role.getRoleName());
        return responseDto;
    }
}
