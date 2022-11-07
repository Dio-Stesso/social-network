package com.benatti.socialnetwork.service.mapper;

import com.benatti.socialnetwork.model.User;
import com.benatti.socialnetwork.model.dto.RoleResponseDto;
import com.benatti.socialnetwork.model.dto.UserRegistrationDto;
import com.benatti.socialnetwork.model.dto.UserResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final RoleMapper roleMapper;

    public UserMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public User mapToModel(UserRegistrationDto requestDto) {
        User user = new User();
        user.setUsername(requestDto.getUsername());
        user.setPassword(requestDto.getPassword());
        user.setEmail(requestDto.getEmail());
        return user;
    }

    public UserResponseDto mapToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setUsername(user.getUsername());
        List<RoleResponseDto> roles = user.getRoles()
                .stream()
                .map(roleMapper::mapToDto)
                .collect(Collectors.toList());
        responseDto.setRoles(roles);
        return responseDto;
    }
}
