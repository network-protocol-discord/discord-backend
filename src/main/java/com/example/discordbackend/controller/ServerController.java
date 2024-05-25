package com.example.discordbackend.controller;

import com.example.discordbackend.dto.Server;
import com.example.discordbackend.dto.ServerRequest;
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
        Server server = serverService.createServer(serverRequest.getServerName());
        return ResponseEntity.ok(server);
    }

    @GetMapping("/{serverId}")
    public ResponseEntity<Server> getServer(@PathVariable String serverId) {
        Server server = serverService.findGroupById(serverId);
        return ResponseEntity.ok(server);
    }
}
