package com.bat.man.cbm.system.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.bat.man.cbm.common.web.WebUtil;
import com.bat.man.cbm.mybatis.plus.domain.RequestPage;
import com.bat.man.cbm.mybatis.plus.domain.ResponsePage;
import com.bat.man.cbm.system.domain.AuthorityGroup;
import com.bat.man.cbm.system.service.AuthorityGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/authorityGroup")
public class AuthorityGroupController {

    @Autowired
    AuthorityGroupService authorityGroupService;

    @GetMapping("/one/{id}")
    ResponseEntity<AuthorityGroup> getOne(@PathVariable String id) {
        AuthorityGroup authorityGroup = authorityGroupService.getOne(id);
        return ResponseEntity.ok(authorityGroup);
    }

    @GetMapping("/all")
    ResponseEntity<List<AuthorityGroup>> getAll() {
        List<AuthorityGroup> authorityGroups = authorityGroupService.getAll();
        return ResponseEntity.ok(authorityGroups);
    }

    @GetMapping("/page")
    ResponseEntity<ResponsePage<AuthorityGroup>> getPage(RequestPage requestPage, HttpServletRequest request) {
        Page<AuthorityGroup> page = new Page<>(requestPage.getPage(), requestPage.getSize());
        page.setCondition(WebUtil.getParamMap(request));
        page = authorityGroupService.getPage(page);
        return ResponseEntity.ok(new ResponsePage<>(page));
    }

    @PostMapping("/create")
    ResponseEntity<AuthorityGroup> create(@RequestBody AuthorityGroup authorityGroup) {
        authorityGroup = authorityGroupService.create(authorityGroup);
        return ResponseEntity.ok(authorityGroup);
    }

    @PostMapping("/update")
    ResponseEntity<AuthorityGroup> update(@RequestBody AuthorityGroup authorityGroup) {
        authorityGroup = authorityGroupService.update(authorityGroup);
        return ResponseEntity.ok(authorityGroup);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Integer> delete(@PathVariable String id) {
        Integer delete = authorityGroupService.delete(id);
        return ResponseEntity.ok(delete);
    }
}
