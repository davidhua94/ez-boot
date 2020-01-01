package com.ezboot.system.role.repository;

import com.ezboot.system.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wang
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>,
        JpaSpecificationExecutor<Role> {

    @Query(value = "SELECT r.role_name FROM t_system_admin_role ar LEFT JOIN t_system_role r ON ar.role_id = r.id WHERE ar.admin_id = ?1"
            , nativeQuery = true)
    List<String> findRoleNameListByAdminId(Integer adminId);
}
