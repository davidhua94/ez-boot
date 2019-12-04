package com.david.system.admin.repository;

import com.david.system.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wang
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    /**
     * 根据用户名和密码查找用户
     * @param username
     * @param password
     * @return
     */
    Admin findAdminByUsernameAndPassword(String username, String password);


    /**
     * 根据用户名查询管理员信息
     * @param username 用户名
     * @return 管理员信息
     */
    Admin findByUsername(String username);


}
