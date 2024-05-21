package com.example.discordbackend.controller;

import com.example.discordbackend.dto.LoginRequest;
import com.example.discordbackend.security.DiscordUserDetailService;
import com.example.discordbackend.security.JwtAuthenticationFilter;
import com.example.discordbackend.security.JwtTokenProvider;
import com.example.discordbackend.security.model.DiscordUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final DiscordUserDetailService discordUserDetailService;

    //로그인 로직
    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, String>> authorize(@Valid @RequestBody LoginRequest loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        // authenticate 메소드가 실행이 될 때 CustomUserDetailsService class의 loadUserByUsername 메소드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 해당 객체를 SecurityContextHolder에 저장하고
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // authentication 객체를 createToken 메소드를 통해서 JWT Token을 생성
        String jwt = jwtTokenProvider.createToken(authentication);

        DiscordUserDetails userDetails = discordUserDetailService.loadUserByUsername(loginDto.getUsername());
        String nickname = userDetails.getNickname();
        String username = userDetails.getUsername();

        HttpHeaders httpHeaders = new HttpHeaders();
        // response header에 jwt token에 넣어줌
        httpHeaders.add(JwtAuthenticationFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        // tokenDto를 이용해 response body에도 넣어서 리턴
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("token", jwt);
        responseBody.put("nickname", nickname);
        responseBody.put("username", username);

        return new ResponseEntity<>(responseBody, httpHeaders, HttpStatus.OK);
    }

    //토큰 유효성 검사
    @PostMapping("/validateToken")
    public ResponseEntity<Map<String, String>> validateToken(@RequestBody Map<String, String> tokenMap) {
        String token = tokenMap.get("token");
        if (jwtTokenProvider.validateToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            DiscordUserDetails userDetails = (DiscordUserDetails) authentication.getPrincipal();
            Map<String, String> responseBody = new HashMap<>();

            responseBody.put("username", userDetails.getUsername());
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
