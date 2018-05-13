package com.bat.man.cbm.system.domain.dto;

public class JwtDto {

    private String _jwt;

    public JwtDto() {

    }

    public JwtDto(String _jwt) {
        this._jwt = _jwt;
    }

    public String get_jwt() {
        return _jwt;
    }

    public void set_jwt(String _jwt) {
        this._jwt = _jwt;
    }

}
