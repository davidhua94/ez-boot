package com.david.system.role.repository;

import com.david.system.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author wang
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>,
        JpaSpecificationExecutor<Role> {

}
