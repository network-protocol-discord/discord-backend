package com.example.discordbackend.dto;

import lombok.Data;

@Data
public class SignalMessage {
    private Object sdp;

    private String key;

    private Object candidate;

    private String roomId;
}
