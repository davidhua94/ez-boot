package com.ezboot.admin.system.repository;

import com.ezboot.admin.system.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author wang
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    /**
     * 根据角色ID查出该角色所有的权限
     */
    @Query(value = "SELECT p.permission FROM t_system_role_permission rp LEFT JOIN t_system_permission p ON rp.permission_id = p.id WHERE rp.role_id = ?1"
            , nativeQuery = true)
    Set<String> getPermissions(Integer roleId);
}
