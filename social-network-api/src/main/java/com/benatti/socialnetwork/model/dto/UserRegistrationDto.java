package com.benatti.socialnetwork.model.dto;

import com.benatti.socialnetwork.validation.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationDto {
    @Email
    private String email;
    @NotEmpty(message = "The password couldn't be empty")
    private String username;
    @NotEmpty(message = "The password couldn't be empty")
    @Size(min = 8, message = "Password must be at least 8 symbols long")
    private String password;
    private String repeatPassword;

}
