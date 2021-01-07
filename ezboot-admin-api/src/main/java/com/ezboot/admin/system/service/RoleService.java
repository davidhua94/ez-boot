package com.ezboot.admin.system.service;

import com.ezboot.admin.system.dto.RoleDTO;
import com.ezboot.admin.system.dto.RoleListQueryDTO;
import com.ezboot.admin.system.entity.Role;
import com.ezboot.core.base.PageResult;
import com.ezboot.core.base.service.BaseService;

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

    List<RoleDTO> listOptions();
}
