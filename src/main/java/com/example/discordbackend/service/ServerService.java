package com.example.discordbackend.service;

import com.example.discordbackend.dto.Server;
import com.example.discordbackend.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ServerService {

    @Autowired
    private ServerRepository serverRepository;

    public Server createServer(String name) {
        return serverRepository.createServer(name);
    }

    public Server findGroupById(String serverId) {
        return serverRepository.findServerById(serverId);
    }
}
