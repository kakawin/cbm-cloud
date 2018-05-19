package com.bat.man.cbm.system.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.bat.man.cbm.system.domain.Authority;

import java.util.List;

public interface AuthorityService {

    Authority getOne(String id);

    List<Authority> getAll();

    Page<Authority> getPage(Page<Authority> page);

    boolean checkSign(String systemSign, String sign);

    Authority create(Authority authority);

    Authority update(Authority authority);

    Integer delete(String id);

}
