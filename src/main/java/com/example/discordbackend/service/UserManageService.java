package com.example.discordbackend.service;

import com.example.discordbackend.dto.UserSignupRequest;
import com.example.discordbackend.exception.UserManageException;
import com.example.discordbackend.exception.UserManageExceptionType;
import com.example.discordbackend.model.Authority;
import com.example.discordbackend.model.User;
import com.example.discordbackend.repository.UserRepository;
import com.example.discordbackend.entity.Users;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserManageService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(UserSignupRequest request) {

        //해당 아이디를 가진 유저가 존재
        if (userRepository.findByUsername(request.getUsername()) != null) {
            throw new UserManageException(
                    UserManageExceptionType.DUPLICATED_SIGNUP_USERNAME.getErrMsg());
        }

        if (userRepository.findByNickname(request.getNickname()) != null) {
            throw new UserManageException(
                    UserManageExceptionType.DUPLICATED_SIGNUP_NICKNAME.getErrMsg());
        }

        User user = User.builder()
                .username(request.getUsername())
                .nickname(request.getNickname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .authority(Authority.ROLE_USER)
                .createdAt(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(user);
        log.info("회원가입 완료. 닉네임: {}, 아이디: {}", savedUser.getNickname(), savedUser.getUsername());
    }

    @Transactional
    public void updatePassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        log.info("비밀번호 변경 완료.");
    }
}