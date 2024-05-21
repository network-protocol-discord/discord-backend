package com.example.discordbackend;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class UserSignupRequest {
    private String userName;
    private String loginId;
    private String password;
    private String email;
}
