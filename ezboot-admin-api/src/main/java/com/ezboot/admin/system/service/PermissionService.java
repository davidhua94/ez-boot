package com.ezboot.admin.system.service;

import java.util.List;
import java.util.Set;

/**
 * @author wang
 */
public interface PermissionService {

    Set<String> getPermissions(List<Integer> roleIds);
}
