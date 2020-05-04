package com.ezboot.controller.system;

import com.ezboot.core.ApiResult;
import com.ezboot.core.annotation.RequiresPermission;
import com.ezboot.core.base.PageResult;
import com.ezboot.system.role.dto.RoleDTO;
import com.ezboot.system.role.dto.RoleListQueryDTO;
import com.ezboot.system.role.entity.Role;
import com.ezboot.system.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author David hua
 * @date 2019-11-10 11:41:10
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    @RequiresPermission("system:role:list")
    public ApiResult<PageResult<Role>> list(RoleListQueryDTO pageQuery) {
        PageResult<Role> rolePageResult = roleService.pageList(pageQuery);
        return ApiResult.success(rolePageResult);
    }

    @GetMapping("/{id}")
    @RequiresPermission("system:role:read")
    public ApiResult<Role> getById(@PathVariable("id") Integer id) {
        return ApiResult.success(roleService.getById(id, Role.class));
    }

    @PostMapping("/save")
    @RequiresPermission("system:role:create")
    public ApiResult addRole(@RequestBody RoleDTO roleDTO) {
        roleService.save(roleDTO);
        return ApiResult.success();
    }

    @PostMapping("/edit")
    @RequiresPermission("system:role:update")
    public ApiResult editRole(@RequestBody RoleDTO roleDTO) {
        roleService.update(roleDTO);
        return ApiResult.success();
    }

    @GetMapping("/delete/{id}")
    @RequiresPermission("system:role:delete")
    public ApiResult deleteRole(@PathVariable("id") Integer id) {
        roleService.delete(id, Role.class);

        return ApiResult.success();
    }

    @GetMapping("/listOptions")
//    @RequiresPermission("system:role:delete")
    public ApiResult listRoleOptions() {
        List<RoleDTO> roleDTOS = roleService.listOptions();
        return ApiResult.success(roleDTOS);
    }
}
