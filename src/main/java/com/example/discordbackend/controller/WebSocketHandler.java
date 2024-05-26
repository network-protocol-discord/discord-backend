package com.example.discordbackend.controller;

import com.example.discordbackend.dto.ChatMessage;
import com.example.discordbackend.dto.ChatRoom;
import com.example.discordbackend.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    // TextMessage 관리 (메세지를 받으면 "Welcome to chatting server"를 session에 전송)
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws  Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);
        //TextMessage textMessage = new TextMessage("Welcome to chatting server");
        //session.sendMessage(textMessage);

        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
        room.handleActions(session, chatMessage, chatService);
    }
}
