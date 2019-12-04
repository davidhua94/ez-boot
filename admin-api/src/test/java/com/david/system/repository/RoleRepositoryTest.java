package com.david.system.repository;

import com.david.ManagementApplicationTests;
import com.david.system.admin.dto.AdminLoginDTO;
import com.david.system.admin.service.AdminService;
import com.david.system.role.entity.Role;
import com.david.system.role.repository.RoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class RoleRepositoryTest extends ManagementApplicationTests {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AdminService adminService;

    @Before
    public void login() {
        AdminLoginDTO adminLoginDTO = new AdminLoginDTO();
        adminLoginDTO.setUsername("test1");
        adminLoginDTO.setPassword("test1");
        adminService.login(adminLoginDTO);
    }

    @Test
    public void testSave() {
        Role role = new Role();
        role.setRoleName("开发");
        role.setDescription("developer");
        role.setEnabled(true);
        role.setCreateName("system");
        role.setUpdateName("system");

        roleRepository.save(role);
    }

    @Test
    public void testUpdate() {
        Optional<Role> byId = roleRepository.findById(2);
        Role role = byId.get();
        role.setEnabled(false);
        roleRepository.save(role);
    }
}
