package com.ezboot.admin.system.service.impl;

import com.ezboot.admin.system.repository.PermissionRepository;
import com.ezboot.admin.system.service.PermissionService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author wang
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Set<String> getPermissions(List<Integer> roleIds) {
        Set<String> permissions = Sets.newHashSet();
        if (roleIds == null || roleIds.isEmpty()) {
            return permissions;
        }

        for (Integer roleId : roleIds) {
            Set<String> permission =  permissionRepository.getPermissions(roleId);
            permissions.addAll(permission);
        }
        return permissions;
    }
}
