package com.ezboot.system.admin.service;

import com.ezboot.core.base.PageResult;
import com.ezboot.core.base.service.BaseService;
import com.ezboot.system.admin.dto.AdminListDTO;
import com.ezboot.system.admin.dto.AdminListQueryDTO;
import com.ezboot.system.admin.dto.AdminLoginDTO;
import com.ezboot.system.admin.entity.Admin;

/**
 * @author wang
 */
public interface AdminService extends BaseService<Admin> {

    Admin findAdminByUsernameAndPassword(String username, String password);

    // void updateAdminById(Admin admin);

    Admin findAdminById(Integer id);

    /**
     * 管理员登陆
     * @param loginRequest 请求参数
     * @return token
     */
    String login(AdminLoginDTO loginRequest);

    PageResult<AdminListDTO> pageList(AdminListQueryDTO queryDTO);
}
