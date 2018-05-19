package com.bat.man.cbm.system.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.bat.man.cbm.system.domain.AuthorityGroup;

import java.util.List;

public interface AuthorityGroupService {

    AuthorityGroup getOne(String id);

    List<AuthorityGroup> getAll();

    Page<AuthorityGroup> getPage(Page<AuthorityGroup> page);

    AuthorityGroup create(AuthorityGroup authorityGroup);

    AuthorityGroup update(AuthorityGroup authorityGroup);

    Integer delete(String id);

}
