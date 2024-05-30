package com.example.discordbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DiscordBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscordBackendApplication.class, args);
    }

}