package com.bat.man.cbm.system.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.bat.man.cbm.common.exception.SystemException;
import com.bat.man.cbm.security.domain.AuthUser;
import com.bat.man.cbm.security.util.AuthUtil;
import com.bat.man.cbm.system.domain.Authority;
import com.bat.man.cbm.system.mapper.AuthorityMapper;
import com.bat.man.cbm.system.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityMapper authorityMapper;

    @Override
    public Authority getOne(String id) {
        return authorityMapper.selectById(id);
    }

    @Override
    public List<Authority> getAll() {
        return authorityMapper.selectList(null);
    }

    @Override
    public Page<Authority> getPage(Page<Authority> page) {
        return page.setRecords(authorityMapper.getAllByCondition(page, page.getCondition()));
    }

    @Override
    public boolean checkSign(String systemSign, String sign) {
        if (AuthUser.ROLE_ADMIN.equals(systemSign + Authority.SEPARATOR + sign)) {
            return false;
        }
        Authority authority = authorityMapper.selectOne(new Authority() {{
            setSign(sign);
            setSystemSign(systemSign);
        }});
        return authority == null;
    }

    @Override
    public Authority create(Authority authority) {
        try {
            Authority.Type.valueOf(authority.getType());
        } catch (Exception e) {
            throw new SystemException("权限类型有误");
        }
        if (checkSign(authority.getSystemSign(), authority.getSign())) {
            throw new SystemException("该权限标识已存在：" + authority.getFullSign());
        }
        //Creator & Updator
        String username = AuthUtil.getUsername();
        authority.setCreator(username);
        authority.setUpdator(username);
        //insert & get
        authorityMapper.insert(authority);
        return authorityMapper.selectById(authority.getId());
    }

    @Override
    public Authority update(Authority authority) {
        //Updator
        authority.setUpdator(AuthUtil.getUsername());
        //update & get
        authorityMapper.updateById(authority);
        return authorityMapper.selectById(authority.getId());
    }

    @Override
    public Integer delete(String id) {
        return authorityMapper.deleteById(id);
    }

}
