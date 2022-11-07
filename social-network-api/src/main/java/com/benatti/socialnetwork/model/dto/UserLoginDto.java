package com.benatti.socialnetwork.model.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDto {
    @NotBlank(message = "Login can't be null or blank!")
    private String username;
    @NotBlank(message = "Password can't be null or blank!")
    private String password;

}
