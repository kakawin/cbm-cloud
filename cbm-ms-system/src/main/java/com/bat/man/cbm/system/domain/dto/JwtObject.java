package com.bat.man.cbm.system.domain.dto;

public class JwtObject {

    private String _jwt;

    public JwtObject() {

    }

    public JwtObject(String _jwt) {
        this._jwt = _jwt;
    }

    public String get_jwt() {
        return _jwt;
    }

    public void set_jwt(String _jwt) {
        this._jwt = _jwt;
    }

}
