package com.bat.man.cbm.system.service;

import com.bat.man.cbm.system.domain.User;

public interface UserService {

    User getOne(String id);

    User getOneByUsername(String username);
}
