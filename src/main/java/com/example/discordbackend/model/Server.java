package com.example.discordbackend.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "server")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Server implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code", nullable = false, length = 10)
    private String code;

    @Column(name="name", nullable = false, length = 50)
    private String name;

    // thumbnail 이미지 추후 구현

    @OneToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "server_user_fk"))
    private User owner;

//    @ManyToMany // ManyToMany 사용하지 않기. 변경해야함 https://ict-nroo.tistory.com/127참고
//    @JoinColumn(name = "userId")
//    private User participant;

    @CreatedDate
    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;
}
