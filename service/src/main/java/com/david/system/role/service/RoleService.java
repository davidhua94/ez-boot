package com.david.system.role.service;

import com.david.core.base.PageResult;
import com.david.core.base.service.BaseService;
import com.david.system.role.dto.RoleDTO;
import com.david.system.role.dto.RoleListQueryDTO;
import com.david.system.role.entity.Role;

/**
 * @author wang
 */
public interface RoleService extends BaseService<Role> {

    void save(RoleDTO role);

    void update(RoleDTO role);

    PageResult<Role> pageList(RoleListQueryDTO pageQuery);
}
