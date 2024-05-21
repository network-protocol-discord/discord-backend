package com.example.discordbackend.security;

import com.example.discordbackend.model.User;
import com.example.discordbackend.repository.UserRepository;
import com.example.discordbackend.security.model.DiscordUserDetails;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class DiscordUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public DiscordUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user != null) {
            return new DiscordUserDetails(user);
        } else {
            throw new UsernameNotFoundException("사용자 아이디를 찾을 수 없습니다");
        }
    }
}
