package com.bat.man.cbm.system.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.bat.man.cbm.system.domain.User;

import java.util.List;

public interface UserService {

    User getOne(String id);

    User getOneByUsername(String username);

    boolean checkUsername(String username);

    List<User> getAll();

    Page<User> getPage(Page<User> page);

    User create(User user);

    User update(User user);

    Integer delete(String id);

}
