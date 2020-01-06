package com.ezboot.system.permission.service;

import java.util.List;
import java.util.Set;

/**
 * @author wang
 */
public interface PermissionService {

    Set<String> getPermissions(List<Integer> roleIds);
}
