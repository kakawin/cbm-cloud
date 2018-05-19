package com.bat.man.cbm.security.domain;

import com.bat.man.cbm.jwt.JwtUser;

public class AuthUser {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    private String id;
    private String username;
    private boolean rememberMe;
    private String nickname;
    private String[] auths;

    public AuthUser() {

    }

    public AuthUser(JwtUser jwtUser, boolean rememberMe) {
        this.setId(jwtUser.getId());
        this.setUsername(jwtUser.getUsername());
        this.setRememberMe(rememberMe);
        this.setNickname(jwtUser.getNickname());
        this.setAuths(jwtUser.getAuths());
    }

    public JwtUser transferJwtUser() {
        return new JwtUser(getId(), getUsername(), getNickname(), getAuths());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String[] getAuths() {
        return auths;
    }

    public void setAuths(String[] auths) {
        this.auths = auths;
    }
}
