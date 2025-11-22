package com.cognizant.cs.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequestdto {

    @NotBlank(message = "Username must not be blank")
    private String userName;

    @NotBlank(message = "Password must not be blank")
    private String password;
}
