package com.example.discordbackend.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Server {
    private String serverId;
    private String name;
    private List<ChatRoom> chatRooms = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    @Builder
    public Server(String serverId, String name) {
        this.serverId = serverId;
        this.name = name;
        chatRooms.add(ChatRoom.create("general"));
    }

    public ChatRoom addChatRoom(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRooms.add(chatRoom);
        return chatRoom;
    }
}
