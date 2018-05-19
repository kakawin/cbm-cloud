package com.bat.man.cbm.system.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.bat.man.cbm.common.web.WebUtil;
import com.bat.man.cbm.mybatis.plus.domain.RequestPage;
import com.bat.man.cbm.mybatis.plus.domain.ResponsePage;
import com.bat.man.cbm.system.domain.User;
import com.bat.man.cbm.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/one/{id}")
    ResponseEntity<User> getOne(@PathVariable String id) {
        User user = userService.getOne(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/oneByUsername/{username}")
    ResponseEntity<User> getOneByUsername(@PathVariable String username) {
        User user = userService.getOneByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/page")
    ResponseEntity<ResponsePage<User>> getPage(RequestPage requestPage, HttpServletRequest request) {
        Page<User> page = new Page<>(requestPage.getPage(), requestPage.getSize());
        page.setCondition(WebUtil.getParamMap(request));
        page = userService.getPage(page);
        return ResponseEntity.ok(new ResponsePage<>(page));
    }

    @PostMapping("/create")
    ResponseEntity<User> create(@RequestBody User user) {
        user = userService.create(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/update")
    ResponseEntity<User> update(@RequestBody User user) {
        user = userService.update(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Integer> delete(@PathVariable String id) {
        Integer delete = userService.delete(id);
        return ResponseEntity.ok(delete);
    }

}
