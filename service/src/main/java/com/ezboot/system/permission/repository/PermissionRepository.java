package com.ezboot.system.permission.repository;

import com.ezboot.system.permission.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wang
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer > {

}
