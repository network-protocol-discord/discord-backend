package com.example.discordbackend.service;

import com.example.discordbackend.dto.Server;
import com.example.discordbackend.dto.User;
import com.example.discordbackend.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ServerService {

    @Autowired
    private ServerRepository serverRepository;

    public Server createServer(String name, String creatorNickname) {
        // 닉네임이 null인지 확인 (추후 프론트에서 확인하도록 수정해도 됨)
        if (creatorNickname == null || creatorNickname.trim().isEmpty()) {
            throw new IllegalArgumentException("Creator nickname cannot be empty");
        }

        Server server = serverRepository.createServer(name);

        // 서버 생성자 유저 생성
        User creator = User.builder()
                .userId(UUID.randomUUID().toString())
                .nickname(creatorNickname)
                .build();

        // 서버의 유저List에 추가
        server.getUsers().add(creator);

        serverRepository.save(server);

        return server;
    }

    public Server findServerById(String serverId) {
        return serverRepository.findServerById(serverId);
    }

    public User addUserToServer(String serverId, String nickname) {
        Server server = serverRepository.findServerById(serverId);
        if (server == null) {
            throw new IllegalArgumentException("Server not found");
        }

        // 닉네임이 동일한 유저 존재여부 확인
        Optional<User> existingUser = server.getUsers().stream().filter(user -> user.getNickname().equals(nickname)).findFirst();
        if (existingUser.isPresent()) {
            return existingUser.get();
        } else {
            User user = User.builder()
                    .userId(UUID.randomUUID().toString())
                    .nickname(nickname)
                    .build();
            server.getUsers().add(user);
            serverRepository.save(server); // 변경사항 저장
            return user;
        }
    }
}
