package com.example.discordbackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@RequiredArgsConstructor
@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketHandler webSocketHandler;

    // WebSocketHandler (ws://localhost:8080/ws/server로 websocket에 접속)
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/ws/server").setAllowedOrigins("*");
        registry.addHandler(webSocketHandler, "/ws/server/{serverId}").setAllowedOrigins("*");

        registry.addHandler(webSocketHandler, "/ws/chat").setAllowedOrigins("*");
    }
}
