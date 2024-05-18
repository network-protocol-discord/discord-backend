package com.example.discordbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;

    @ManyToOne
    @JoinColumn(name = "channelId")
    private Channel channel;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    // text만 허용 (image or file x)
    @Column(nullable = false)
    private String content;

    // isVolatile (channel이 음성통화 유형일 때 휘발성 채팅)

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
