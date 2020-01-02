package com.ezboot.core.securty;

import com.ezboot.core.CurrentAdmin;
import com.ezboot.core.exception.ServiceException;
import com.ezboot.core.util.GsonUtil;
import com.ezboot.core.util.JedisUtil;
import com.ezboot.system.admin.constant.AdminCode;
import com.ezboot.system.admin.entity.Admin;
import com.ezboot.system.admin.repository.AdminRepository;
import com.ezboot.system.permission.repository.PermissionRepository;
import com.ezboot.system.role.repository.RoleRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author David Hua
 * @date 2020/1/2
 * @desc
 */
@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin entity = adminRepository.findByUsername(username);
        if (entity == null) {
            throw new ServiceException(AdminCode.USERNAME_NOT_EXIST);
        }
        CurrentAdmin currentAdmin = new CurrentAdmin();
        BeanUtils.copyProperties(entity, currentAdmin);

        // todo 添加角色和权限进去
        currentAdmin.setRoles(null);
        currentAdmin.setPermissions(null);
        return currentAdmin;
    }
}
