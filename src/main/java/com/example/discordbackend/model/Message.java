package com.example.discordbackend.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Message implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "channel_id", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "message_channel_fk"))
    private Channel channel;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "message_user_fk"))
    private User user;

    // text만 허용 (image or file x)
    @Column(name="content", columnDefinition = "longtext")
    private String content;

    // isVolatile (channel이 음성통화 유형일 때 휘발성 채팅)

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
