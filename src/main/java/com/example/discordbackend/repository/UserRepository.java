package com.example.discordbackend.repository;

import com.example.discordbackend.model.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.example.discordbackend.model.QUser.user;

@Repository
public class UserRepository extends CommonRepository<User, Long>{

    private final JPAQueryFactory jpaQueryFactory;

    protected UserRepository(JPAQueryFactory jpaQueryFactory) {
        super(User.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public User findByUsername(String username) {
        return jpaQueryFactory.selectFrom(user)
                .where(user.username.eq(username))
                .fetchOne();
    }

    public User findByNickname(String nickname) {
        return jpaQueryFactory.selectFrom(user)
                .where(user.nickname.eq(nickname))
                .fetchOne();
    }
}
