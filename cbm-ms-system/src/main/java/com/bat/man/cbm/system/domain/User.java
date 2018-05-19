package com.bat.man.cbm.system.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import com.bat.man.cbm.mybatis.plus.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

@TableName("tb_system_user")
public class User extends BaseEntity {

    public static final String DEFAULT_PASSWORD = "12345678";

    /**
     * 帐号昵称
     */
    private String nickname;
    /**
     * 帐号名
     */
    private String username;
    /**
     * 登录密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    /**
     * 状态，默认可用
     */
    private Integer state = State.ENABLE.value();
    /**
     * 备注
     */
    private String remark;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public enum State {

        ENABLE(1, "可用"),

        DISABLE(0, "不可用");

        private final int value;

        private final String desc;

        private State(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int value() {
            return this.value;
        }

        public String desc() {
            return this.desc;
        }
    }
}
