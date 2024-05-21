package com.example.discordbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSignupRequest {

    @NotBlank
    @Size(min = 2, max = 50)
    private String username;

    @NotBlank
    @Size(min = 5, max = 50)
    private String email;

    @NotBlank
    @Size(min = 2, max = 50)
    private String nickname;

    @NotBlank
    @Size(min = 8, max = 50)
    private String password;


}
