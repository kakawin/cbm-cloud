package com.bat.man.cbm.system.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.bat.man.cbm.common.web.WebUtil;
import com.bat.man.cbm.mybatis.plus.domain.RequestPage;
import com.bat.man.cbm.mybatis.plus.domain.ResponsePage;
import com.bat.man.cbm.system.domain.Authority;
import com.bat.man.cbm.system.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/authority")
public class AuthorityController {

    @Autowired
    AuthorityService authorityService;

    @GetMapping("/one/{id}")
    ResponseEntity<Authority> getOne(@PathVariable String id) {
        Authority authority = authorityService.getOne(id);
        return ResponseEntity.ok(authority);
    }

    @GetMapping("/all")
    ResponseEntity<List<Authority>> getAll() {
        List<Authority> authoritys = authorityService.getAll();
        return ResponseEntity.ok(authoritys);
    }

    @GetMapping("/page")
    ResponseEntity<ResponsePage<Authority>> getPage(RequestPage requestPage, HttpServletRequest request) {
        Page<Authority> page = new Page<>(requestPage.getPage(), requestPage.getSize());
        page.setCondition(WebUtil.getParamMap(request));
        page = authorityService.getPage(page);
        return ResponseEntity.ok(new ResponsePage<>(page));
    }

    @PostMapping("/create")
    ResponseEntity<Authority> create(@RequestBody Authority authority) {
        authority = authorityService.create(authority);
        return ResponseEntity.ok(authority);
    }

    @PostMapping("/update")
    ResponseEntity<Authority> update(@RequestBody Authority authority) {
        authority = authorityService.update(authority);
        return ResponseEntity.ok(authority);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Integer> delete(@PathVariable String id) {
        Integer delete = authorityService.delete(id);
        return ResponseEntity.ok(delete);
    }

}
