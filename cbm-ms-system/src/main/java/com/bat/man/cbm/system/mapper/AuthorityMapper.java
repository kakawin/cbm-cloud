package com.bat.man.cbm.system.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.bat.man.cbm.mybatis.plus.SuperMapper;
import com.bat.man.cbm.system.domain.Authority;

import java.util.List;
import java.util.Map;

public interface AuthorityMapper extends SuperMapper<Authority> {

    List<Authority> getAllByCondition(Pagination page, Map<String, Object> condition);

}
