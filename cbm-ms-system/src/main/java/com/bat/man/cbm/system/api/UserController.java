package com.bat.man.cbm.system.api;

import com.bat.man.cbm.system.domain.User;
import com.bat.man.cbm.system.service.UserService;
import com.bat.man.cbm.system.util.PasswordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('system-user-view')")
    @GetMapping("/{id}")
    User getOne(@PathVariable String id) {
        return userService.getOne(id);
    }

}
