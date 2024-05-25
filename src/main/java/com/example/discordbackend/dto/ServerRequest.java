package com.example.discordbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerRequest {
    private String serverName;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
