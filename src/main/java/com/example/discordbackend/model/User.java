package com.example.discordbackend.model;

import com.example.discordbackend.dto.UserSignupRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //login 아이디
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    @Setter
    private String password;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name= "nickname", nullable = false, length = 30)
    private String nickname;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority", nullable = false)
    private Authority authority;
}
