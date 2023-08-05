package com.hodolog.api.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class UserPrincpal extends User {

    private final Long userId;

    public UserPrincpal(com.hodolog.api.domain.User user) {
        super(user.getEmail(), user.getPassword(),  List.of(new SimpleGrantedAuthority("ADMIN")));
        this.userId = user.getId();
    }

    public Long getUserId() {
        return userId;
    }
}
