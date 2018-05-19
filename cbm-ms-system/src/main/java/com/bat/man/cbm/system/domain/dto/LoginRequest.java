package com.bat.man.cbm.system.domain.dto;

public class LoginRequest {

    private String username;

    private String password;

    private boolean reme;

    public LoginRequest() {

    }

    public LoginRequest(String username, String password, boolean reme) {
        this.username = username;
        this.password = password;
        this.reme = reme;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isReme() {
        return reme;
    }

    public void setReme(boolean reme) {
        this.reme = reme;
    }
}
