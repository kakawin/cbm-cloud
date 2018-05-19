package com.bat.man.cbm.system.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.bat.man.cbm.security.util.AuthUtil;
import com.bat.man.cbm.system.domain.AuthorityGroup;
import com.bat.man.cbm.system.mapper.AuthorityGroupMapper;
import com.bat.man.cbm.system.service.AuthorityGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityGroupServiceImpl implements AuthorityGroupService {

    @Autowired
    AuthorityGroupMapper authorityGroupMapper;

    @Override
    public AuthorityGroup getOne(String id) {
        return authorityGroupMapper.selectById(id);
    }

    @Override
    public List<AuthorityGroup> getAll() {
        return authorityGroupMapper.selectList(null);
    }

    @Override
    public Page<AuthorityGroup> getPage(Page<AuthorityGroup> page) {
        return page.setRecords(authorityGroupMapper.getAllByCondition(page, page.getCondition()));
    }

    @Override
    public AuthorityGroup create(AuthorityGroup authorityGroup) {
        //Creator & Updator
        String username = AuthUtil.getUsername();
        authorityGroup.setCreator(username);
        authorityGroup.setUpdator(username);
        //insert & get
        authorityGroupMapper.insert(authorityGroup);
        return authorityGroupMapper.selectById(authorityGroup.getId());
    }

    @Override
    public AuthorityGroup update(AuthorityGroup authorityGroup) {
        //Updator
        authorityGroup.setUpdator(AuthUtil.getUsername());
        //update & get
        authorityGroupMapper.updateById(authorityGroup);
        return authorityGroupMapper.selectById(authorityGroup.getId());
    }

    @Override
    public Integer delete(String id) {
        return authorityGroupMapper.deleteById(id);
    }

}
