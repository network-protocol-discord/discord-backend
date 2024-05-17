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
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int channelId;

    @ManyToOne
    @JoinColumn(name = "serverId")
    private Server includedIn;

    // text || voice
    private String type;

    @Column(nullable = false, length = 50)
    private String channelName;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
