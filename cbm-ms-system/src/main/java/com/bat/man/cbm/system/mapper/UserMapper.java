package com.bat.man.cbm.system.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.bat.man.cbm.mybatis.plus.SuperMapper;
import com.bat.man.cbm.system.domain.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends SuperMapper<User> {

    List<User> getAllByCondition(Pagination page, Map<String, Object> condition);
}
