package com.example.discordbackend.controller;

import com.example.discordbackend.dto.UserSignupRequest;
import com.example.discordbackend.exception.UserManageException;
import com.example.discordbackend.service.UserManageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/")
public class UserManageController {

    private final UserManageService userManageService;

    @GetMapping("/check")
    public ResponseEntity<Void> check() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserSignupRequest userSignupRequest) {
        try {
            userManageService.signup(userSignupRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserManageException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }
}
