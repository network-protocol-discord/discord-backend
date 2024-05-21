package com.example.discordbackend.security.model;

import com.example.discordbackend.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class DiscordUserDetails implements UserDetails {

    private String username;
    private String password;
    private String nickname; // 사용자 닉네임
    private Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

    public DiscordUserDetails(User user) {
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.password = user.getPassword();
        this.authorities.add(new SimpleGrantedAuthority(user.getAuthority().name()));
    }

    public DiscordUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = (Collection<SimpleGrantedAuthority>) authorities;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
