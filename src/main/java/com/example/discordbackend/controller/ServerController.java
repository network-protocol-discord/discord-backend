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

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/server")
public class ServerController {
    @Autowired
    private ServerService serverService;

    @PostMapping
    public ResponseEntity<Server> createServer(@RequestParam("name") String name) {
        Server server = serverService.createServer(name);
        return ResponseEntity.ok(server);
    }

    @GetMapping
    public ResponseEntity<List<Server>> getAll() {
        List<Server> servers = serverService.getAll();
        return ResponseEntity.ok(servers);
    }

    @GetMapping("/{serverId}")
    public ResponseEntity<Server> getServer(@PathVariable String serverId) {
        Server server = serverService.findServerById(serverId);
        return ResponseEntity.ok(server);
    }

    // 서버 삭제
    @DeleteMapping
    public String deleteRoom(@PathVariable String serverId) {
        return serverService.deleteServer(serverId);
    }

//    @PostMapping("/{serverId}/join")
//    public ResponseEntity<User> joinServer(@PathVariable String serverId, @RequestBody UserRequest userRequest) {
//        User user = serverService.addUserToServer(serverId, userRequest.getNickname());
//        return ResponseEntity.ok(user);
//    }
//
//    @PostMapping("/{serverId}/addUser")
//    public ResponseEntity<User> addUserToServer(@PathVariable String serverId, @RequestBody UserRequest userRequest) {
//        User user = serverService.addUserToServer(serverId, userRequest.getNickname());
//        return ResponseEntity.ok(user);
//    }
}
