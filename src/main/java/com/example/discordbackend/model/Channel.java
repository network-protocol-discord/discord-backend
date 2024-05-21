package com.example.discordbackend.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Channel implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "server_id", referencedColumnName = "id",
            nullable = false, foreignKey = @ForeignKey(name = "channel_server_fk"))
    private Server server;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
