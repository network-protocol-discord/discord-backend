package com.example.discordbackend;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class UserSignupRequest {
    private String username;
    private String userid;
    private String password;
}