package com.ezboot.system.role.service;

import com.ezboot.core.base.PageResult;
import com.ezboot.core.base.service.BaseService;
import com.ezboot.system.role.dto.RoleDTO;
import com.ezboot.system.role.dto.RoleListQueryDTO;
import com.ezboot.system.role.entity.Role;

import java.util.List;

/**
 * @author wang
 */
public interface RoleService extends BaseService<Role> {

    void save(RoleDTO role);

    void update(RoleDTO role);

    PageResult<Role> pageList(RoleListQueryDTO pageQuery);

    /**
     * 根据管理员ID获取此管理员的角色
     */
    List<Integer> getRoleIdsByAdminId(Integer id);
}
