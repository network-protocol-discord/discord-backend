package com.example.discordbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerRequest {
    private String serverName;
    private String creatorNickname;

    public String getServerName() {
        return serverName;
    }

    public String getCreatorNickname() {
        return creatorNickname;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setCreatorNickname(String creatorNickname) {
        this.creatorNickname = creatorNickname;
    }
}
