package com.example.discordbackend.service;

import com.example.discordbackend.exception.UserManageException;
import com.example.discordbackend.exception.UserManageExceptionType;
import com.example.discordbackend.repository.UserRepository;
import com.example.discordbackend.UserSignupRequest;
import com.example.discordbackend.entity.Users;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j

public class UserManageService {

    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(UserSignupRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserManageException(
                    UserManageExceptionType.DUPLICATED_SIGNUP_USERNAME.getErrMsg());
        }

        Users users = Users.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .userid(request.getUserid())
                .roles("ROLE_USER")
                .build();
        userRepository.save(users);
    }
}