package com.ezboot.core.security;

import com.ezboot.system.admin.repository.AdminRepository;
import com.ezboot.system.permission.repository.PermissionRepository;
import com.ezboot.system.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author David Hua
 * @date 2020/1/2
 * @desc
 */
@Service
public class CustomUserService {
        //implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Admin entity = adminRepository.findByUsername(username);
//        if (entity == null) {
//            throw new ServiceException(AdminCode.USERNAME_NOT_EXIST);
//        }
//        CurrentAdmin currentAdmin = new CurrentAdmin();
//        BeanUtils.copyProperties(entity, currentAdmin);
//
//        // todo 添加角色和权限进去
//        currentAdmin.setRoles(null);
//        currentAdmin.setPermissions(null);
//        return currentAdmin;
//    }
}
