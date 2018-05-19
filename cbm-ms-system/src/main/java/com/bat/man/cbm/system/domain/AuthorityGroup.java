package com.bat.man.cbm.system.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import com.bat.man.cbm.mybatis.plus.domain.BaseEntity;

@TableName("tb_system_authority_group")
public class AuthorityGroup extends BaseEntity {

    /**
     * 权限组名称
     */
    private String name;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
