package com.bat.man.cbm.system.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import com.bat.man.cbm.mybatis.plus.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName("tb_system_authority")
public class Authority extends BaseEntity {

    public static final String SEPARATOR = "_";

    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限标识
     */
    private String sign;
    /**
     * 系统标识
     */
    private String systemSign;
    /**
     * 权限类型 {@link com.bat.man.cbm.system.domain.Authority.Type}
     */
    private String type;
    /**
     * 备注
     */
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSystemSign() {
        return systemSign;
    }

    public void setSystemSign(String systemSign) {
        this.systemSign = systemSign;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty(value = "fullSign", access = JsonProperty.Access.READ_ONLY)
    public String getFullSign() {
        return this.systemSign + SEPARATOR + this.sign;
    }

    public enum Type {

        FUNC("func", "功能"),

        MENU("menu", "菜单");

        private final String value;
        private final String desc;

        private Type(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public String value() {
            return this.value;
        }

        public String desc() {
            return this.desc;
        }
    }
}
