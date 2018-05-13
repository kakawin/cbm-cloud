package com.bat.man.cbm.jwt;

public class JwtUser {

    private String id;
    private String nickname;
    private String username;
    private String[] auths;

    public JwtUser() {
    }

    public JwtUser(String id, String username, String nickname, String[] auths) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.auths = auths;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public String[] getAuths() {
        return auths;
    }
}
