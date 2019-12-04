package com.ezboot.system.admin;


import com.ezboot.ManagementApplicationTests;
import com.ezboot.system.admin.entity.Admin;
import com.ezboot.system.admin.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class AdminRepositoryTest extends ManagementApplicationTests {

    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void adminTest(){
        List<Admin> list = adminRepository.findAll();
        for (Admin admin : list){
            log.info(admin.toString());
        }
    }

    @Test
    public void findTest(){
        Object admin = adminRepository.findById(3);
        log.info(admin.toString());
    }

    @Test
    public void saveTest(){
        Admin admin = new Admin();
        admin.setUsername("test7");
        admin.setPassword("test7");
        adminRepository.save(admin);
    }

    @Test
    public void findAdminByNameAndPasswordTest(){
        Admin admin = adminRepository.findAdminByUsernameAndPassword("wan9", "test3");
        log.info(admin.toString());
    }

//    @Test
//    public void updateAdminById(){
//        Admin admin = adminRepository.findAdminById(3);
//        admin.setLastLoginIp("127.0.0.1");
//        admin.setLastLoginTime(new Date());
//        adminRepository.updateAdminById(admin);
//    }

    @Test
    public void findByUsername() {
        Admin admin = adminRepository.findByUsername("test1");
        Assert.assertNotNull(admin);
    }
}
