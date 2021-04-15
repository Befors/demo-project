package com.haulmont.demoproject.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    CLIENT, ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_" + toString();
    }
}
