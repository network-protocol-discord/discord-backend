package com.example.discordbackend.repository;

import com.example.discordbackend.dto.Server;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ServerRepository {
    private Map<String, Server> serverMap = new ConcurrentHashMap<>();

    public Server createServer(String name) {
        // random UUID로 server ID 생성
        String serverId = UUID.randomUUID().toString();

        // server Id와 사용자가 입력한 name으로 새로운 서버 생성
        Server server = new Server(serverId, name);
        serverMap.put(serverId, server);

        return server;
    }

    public Server findServerById(String serverId) {
        return serverMap.get(serverId);
    }
}
