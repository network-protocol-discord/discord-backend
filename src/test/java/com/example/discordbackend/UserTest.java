package com.example.discordbackend;

import com.example.discordbackend.dto.UserSignupRequest;
import com.example.discordbackend.model.User;
import com.example.discordbackend.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("mysql과 spring연결, querydsl세팅이 제대로 되었는지 테스트")
@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void add(){
        UserSignupRequest request = UserSignupRequest.builder()
                .username("이름")
                .loginId("helloworld")
                .password("password")
                .email("asdfasdf@hanyang.ac.kr")
                .build();
        User user = User.of(request);
        userRepository.save(user);

        Assertions.assertNotNull(user.getId());
    }
}
