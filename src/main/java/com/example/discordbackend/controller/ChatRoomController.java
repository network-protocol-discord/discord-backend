package com.example.discordbackend.controller;

import com.example.discordbackend.dto.Server;
import com.example.discordbackend.service.ServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.example.discordbackend.dto.ChatRoom;
import com.example.discordbackend.repository.ChatRoomRepository;
import com.example.discordbackend.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/server/{serverId}/chat")
@Slf4j
public class ChatRoomController {

    @Autowired
    private final ChatRoomRepository chatRoomRepository;
    @Autowired
    private ServerService serverService;

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model, @PathVariable String serverId) {
        // 서버가 존재하는지 확인
        log.debug("Received serverId: {}", serverId);
        Server server = serverService.findServerById(serverId);
        if (server != null) {
            //model.addAttribute("serverId", serverId);
            return "/chat/room"; // 채팅 리스트 화면 반환
        } else {
            model.addAttribute("message", "존재하지 않은 서버입니다.");
            return "/error"; // 에러 페이지로 리다이렉트하거나 에러 메시지를 표시
        }
    }


    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room(@PathVariable String serverId) {
        return serverService.findServerById(serverId).getChatRooms();
    }

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@PathVariable String serverId, @RequestParam String name) {
        return serverService.createRoom(serverId, name);
    }

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String serverId, @PathVariable String roomId) {
        model.addAttribute("serverId", serverId);
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }

    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }
}
