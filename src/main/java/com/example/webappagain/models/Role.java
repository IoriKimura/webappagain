package com.example.webappagain.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    worker,
    manager;

    @Override
    public String getAuthority() {
        return name();
    }
}
