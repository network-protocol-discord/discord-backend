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
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serverId;

    @Column(nullable = false, length = 10)
    private String serverCode;

    @Column(nullable = false, length = 50)
    private String serverName;

    // thumbnail 이미지 추후 구현

    @OneToOne
    @JoinColumn(name = "userId")
    private User owner;

//    @ManyToMany // ManyToMany 사용하지 않기. 변경해야함 https://ict-nroo.tistory.com/127참고
//    @JoinColumn(name = "userId")
//    private User participant;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
