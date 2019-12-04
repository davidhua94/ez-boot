package com.ezboot.system.permission.service.impl;

import com.ezboot.system.permission.repository.PermissionRepository;
import com.ezboot.system.permission.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wang
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

}
