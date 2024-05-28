package com.example.discordbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DiscordBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscordBackendApplication.class, args);
    }

}