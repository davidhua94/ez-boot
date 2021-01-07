package com.ezboot.admin.system.repository;

import com.ezboot.admin.system.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author wang
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>, JpaSpecificationExecutor<Admin> {

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


    @Modifying
    @Query("UPDATE Admin a SET a.lastLoginIp=:lastLoginIp,a.lastLoginTime=:now,a.updateTime=:now where a.id=:id")
    int updateLoginStatus(@Param("id") Integer id,
                          @Param("lastLoginIp") String lastLoginIp,
                          @Param("now") Date now);
}
