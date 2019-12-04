package com.david.system.admin.service;

import com.david.system.admin.dto.AdminLoginDTO;
import com.david.system.admin.entity.Admin;

/**
 * @author wang
 */
public interface AdminService{

    Admin findAdminByUsernameAndPassword(String username, String password);

    // void updateAdminById(Admin admin);

    Admin findAdminById(Integer id);

    /**
     * 管理员登陆
     * @param loginRequest 请求参数
     * @return token
     */
    String login(AdminLoginDTO loginRequest);
}
