package com.example.discordbackend.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

    private String userId;
    private String nickname;
    private String camKey;

    @Builder
    public User(String userId, String nickname) {
        this.userId = userId;
        this.nickname = nickname;
    }
}
