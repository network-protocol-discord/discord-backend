package com.example.discordbackend.controller;

import com.example.discordbackend.dto.ChatMessage;
import com.example.discordbackend.dto.SignalMessage;
import com.example.discordbackend.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@Slf4j
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;

//    private final ServerService serverService;

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        message.setTime(LocalDateTime.now());
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
//            serverService.addUserToServer(message.getRoomId(), message.getSender());
        }
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping("/video/peer/offer")
    public void peerOffer(@Payload SignalMessage signalMessage) {

        log.info("[sdp]: {}", signalMessage.getSdp());
        messagingTemplate.convertAndSend("/sub/video/peer/offer/" + signalMessage.getRoomId(), signalMessage);
    }

    @MessageMapping("/video/peer/candidate")
    public void peerIceCandidate(@Payload SignalMessage signalMessage) {

        log.info("[candidate]: {}", signalMessage.getCandidate());
        messagingTemplate.convertAndSend("/sub/video/peer/candidate" + signalMessage.getRoomId(), signalMessage);
    }

    @MessageMapping("/video/peer/answer")
    public void peerAnswer(@Payload SignalMessage signalMessage) {

        log.info("[sdp]: {}", signalMessage.getSdp());
        messagingTemplate.convertAndSend("/sub/video/peer/answer" + signalMessage.getRoomId(), signalMessage);
    }
}