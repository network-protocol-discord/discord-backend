package com.example.discordbackend.service;

import com.example.discordbackend.model.User;
import com.example.discordbackend.repository.UserRepository;
import com.example.discordbackend.security.model.DiscordUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

//현재 로그인된(인증된) 유저를 불러오기
@Component
@RequiredArgsConstructor
@Slf4j
public class IdentityService {

    private final UserRepository userRepository;

    //현재 인증된 유저 가져오기
    public User getCurrentUser() {
        log.info("현재 사용자를 가져옵니다");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            return null;
        }
        DiscordUserDetails userDetails = (DiscordUserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        log.info("현재 유저 : {}", username);
        return userRepository.findByUsername(username);
    }
}
