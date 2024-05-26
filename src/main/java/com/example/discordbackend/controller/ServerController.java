package com.example.discordbackend.controller;

import com.example.discordbackend.dto.Server;
import com.example.discordbackend.dto.ServerRequest;
import com.example.discordbackend.dto.User;
import com.example.discordbackend.dto.UserRequest;
import com.example.discordbackend.service.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/server")
public class ServerController {
    @Autowired
    private ServerService serverService;

    @PostMapping
    public ResponseEntity<Server> createServer(@RequestBody ServerRequest serverRequest) {
        Server server = serverService.createServer(serverRequest.getServerName(), serverRequest.getCreatorNickname());
        return ResponseEntity.ok(server);
    }

    @GetMapping("/{serverId}")
    public ResponseEntity<Server> getServer(@PathVariable String serverId) {
        Server server = serverService.findServerById(serverId);
        return ResponseEntity.ok(server);
    }

    @PostMapping("/{serverId}/join")
    public ResponseEntity<User> joinServer(@PathVariable String serverId, @RequestBody UserRequest userRequest) {
        User user = serverService.addUserToServer(serverId, userRequest.getNickname());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/{serverId}/addUser")
    public ResponseEntity<User> addUserToServer(@PathVariable String serverId, @RequestBody UserRequest userRequest) {
        User user = serverService.addUserToServer(serverId, userRequest.getNickname());
        return ResponseEntity.ok(user);
    }
}
