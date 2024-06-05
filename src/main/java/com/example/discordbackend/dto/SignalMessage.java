package com.example.discordbackend.dto;

import lombok.Data;

@Data
public class SignalMessage {
    private String sdp;

    private String senderId;

    private String receiverId;

    private String candidate;

    private String roomId;
}
