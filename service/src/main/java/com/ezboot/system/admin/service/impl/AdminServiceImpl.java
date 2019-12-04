package com.ezboot.system.admin.service.impl;

import com.ezboot.core.CurrentAdmin;
import com.ezboot.core.constant.GlobalConstants;
import com.ezboot.core.util.GsonUtil;
import com.ezboot.core.util.JedisUtil;
import com.ezboot.core.util.UUIDGenerator;
import com.ezboot.system.admin.constant.AdminCode;
import com.ezboot.system.admin.dto.AdminLoginDTO;
import com.ezboot.system.admin.entity.Admin;
import com.ezboot.system.admin.exception.LoginException;
import com.ezboot.system.admin.repository.AdminRepository;
import com.ezboot.system.admin.service.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wang
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin findAdminByUsernameAndPassword(String username, String password) {
        return adminRepository.findAdminByUsernameAndPassword(username,password);
    }

    @Override
    public Admin findAdminById(Integer id) {
        return null;
    }

    @Override
    public String login(AdminLoginDTO loginRequest) {
        Admin admin = adminRepository.findByUsername(loginRequest.getUsername());
        if (admin == null) {
            throw new LoginException(AdminCode.USERNAME_NOT_EXIST);
        }

        if (!admin.isEnabled()) {
            throw new LoginException(AdminCode.USERNAME_LOCKED);
        }

        String encryptPwd = encryptPwd(loginRequest.getPassword());

        if (!encryptPwd.equals(admin.getPassword())) {
            throw new LoginException(AdminCode.USERNAME_PASSWORD_UN_MATCH);
        }

        // 更新上次登陆时间、登陆ip

        // TODO 查出当前用户的角色、权限等信息
        CurrentAdmin currentUser = new CurrentAdmin();
        BeanUtils.copyProperties(admin, currentUser);

        String token = UUIDGenerator.randomUUID();
        // 默认30分钟
        JedisUtil.put(token, GsonUtil.obj2Str(currentUser));
        JedisUtil.setExpire(token, GlobalConstants.ADMIN_TOKEN_EXPIRE);

        return token;
    }

    /**
     * TODO 数据库密码加密
     * @param password 加密后的密码
     */
    private String encryptPwd(String password) {
        return password;
    }

}
