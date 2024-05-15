package com.example.simple_chatting.rest;

import com.example.simple_chatting.VO.SocketVO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {
    @MessageMapping("/receive")
    @SendTo("/send")

    public SocketVO SocketHandler(SocketVO socketVO) {
        String userName = socketVO.getUserName();
        String content = socketVO.getContent();

        SocketVO result = new SocketVO(userName, content);
        return result;
    }
}
