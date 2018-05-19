package com.bat.man.cbm.system.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.bat.man.cbm.common.exception.SystemException;
import com.bat.man.cbm.security.util.AuthUtil;
import com.bat.man.cbm.security.util.PasswordManager;
import com.bat.man.cbm.system.domain.User;
import com.bat.man.cbm.system.mapper.UserMapper;
import com.bat.man.cbm.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getOne(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public User getOneByUsername(String username) {
        return userMapper.selectOne(new User() {{
            setUsername(username);
        }});
    }

    @Override
    public boolean checkUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return false;
        }
        User user = getOneByUsername(username);
        return user == null;
    }

    @Override
    public List<User> getAll() {
        return userMapper.selectList(null);
    }

    @Override
    public Page<User> getPage(Page<User> page) {
        return page.setRecords(userMapper.getAllByCondition(page, page.getCondition()));
    }

    @Override
    public User create(User user) {
        if (checkUsername(user.getUsername())) {
            throw new SystemException("该用户名不可用");
        }
        //Creator & Updator
        String username = AuthUtil.getUsername();
        user.setCreator(username);
        user.setUpdator(username);
        //CreateTime
        Date now = new Date();
        user.setCreateTime(now);
        //Password
        String password = user.getPassword();
        if (StringUtils.isEmpty(password)) {
            password = PasswordManager.encode(User.DEFAULT_PASSWORD);
        } else {
            password = PasswordManager.encode(password);
        }
        user.setPassword(password);
        //insert & get
        userMapper.insert(user);
        return userMapper.selectById(user.getId());
    }

    @Override
    public User update(User user) {
        //Updator
        user.setUpdator(AuthUtil.getUsername());
        //Password
        String password = user.getPassword();
        if (!StringUtils.isEmpty(password)) {
            password = PasswordManager.encode(password);
        }
        user.setPassword(password);
        //update & get
        userMapper.updateById(user);
        return userMapper.selectById(user.getId());
    }

    @Override
    public Integer delete(String id) {
        return userMapper.deleteById(id);
    }
}
