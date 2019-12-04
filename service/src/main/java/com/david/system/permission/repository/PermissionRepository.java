package com.david.system.permission.repository;

import com.david.system.permission.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wang
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer > {

}
