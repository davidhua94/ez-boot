package com.ezboot.admin.system.service;

import com.ezboot.admin.system.dto.AdminListDTO;
import com.ezboot.admin.system.dto.AdminListQueryDTO;
import com.ezboot.admin.system.dto.AdminLoginDTO;
import com.ezboot.admin.system.dto.ResetPasswordDTO;
import com.ezboot.admin.system.entity.Admin;
import com.ezboot.core.base.PageResult;
import com.ezboot.core.base.service.BaseService;

import java.util.Set;

/**
 * @author wang
 */
public interface AdminService extends BaseService<Admin> {

    /**
     * 管理员登陆
     * @param loginRequest 请求参数
     * @return token
     */
    String login(AdminLoginDTO loginRequest);

    PageResult<AdminListDTO> pageList(AdminListQueryDTO queryDTO);

    Set<String> getPermissions(Integer adminId);

    void resetPassword(ResetPasswordDTO resetPasswordDTO);
}
