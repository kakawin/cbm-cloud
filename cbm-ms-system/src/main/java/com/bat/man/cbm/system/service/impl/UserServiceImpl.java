package com.bat.man.cbm.system.service.impl;

import com.bat.man.cbm.system.domain.User;
import com.bat.man.cbm.system.mapper.UserMapper;
import com.bat.man.cbm.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
