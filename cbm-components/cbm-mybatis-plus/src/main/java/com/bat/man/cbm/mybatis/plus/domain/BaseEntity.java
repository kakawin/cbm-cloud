package com.bat.man.cbm.mybatis.plus.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.Date;

public class BaseEntity {
    /**
     * 主键UUID
     */
    @TableId
    private String id;
    /**
     * 创建时间
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createTime;
    /**
     * 创建人
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String creator;
    /**
     * 更新时间
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp updateTime;
    /**
     * 更新人
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String updator;
    /**
     * 删除，默认不删除
     */
    @TableLogic
    @JsonIgnore
    private Integer deleted = Deleted.NO.value();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public enum Deleted {

        YES(1, "已删除"),

        NO(0, "未删除");

        private final int value;

        private final String desc;

        private Deleted(int value, String desc) {
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

