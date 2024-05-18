package com.example.discordbackend.controller;

import com.example.discordbackend.UserSignupRequest;
import com.example.discordbackend.service.UserManageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/user/manage")

public class UserManageController {

    private final UserManageService userManageService;

    @GetMapping("/check")
    public ResponseEntity<Void> check() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody UserSignupRequest userSignupRequest) {
            userManageService.signup(userSignupRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
