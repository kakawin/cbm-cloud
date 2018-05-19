package com.bat.man.cbm.system.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.bat.man.cbm.mybatis.plus.SuperMapper;
import com.bat.man.cbm.system.domain.AuthorityGroup;

import java.util.List;
import java.util.Map;

public interface AuthorityGroupMapper extends SuperMapper<AuthorityGroup> {

    List<AuthorityGroup> getAllByCondition(Pagination page, Map<String, Object> condition);

}
