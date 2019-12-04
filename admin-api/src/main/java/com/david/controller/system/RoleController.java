package com.david.controller.system;

import com.david.core.ApiResult;
import com.david.core.base.PageResult;
import com.david.system.role.dto.RoleDTO;
import com.david.system.role.dto.RoleListQueryDTO;
import com.david.system.role.entity.Role;
import com.david.system.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ApiResult<PageResult<Role>> list(@RequestBody RoleListQueryDTO pageQuery) {
        PageResult<Role> rolePageResult = roleService.pageList(pageQuery);
        return ApiResult.success(rolePageResult);
    }

    @PostMapping("")
    public ApiResult addRole(@RequestBody RoleDTO roleDTO) {
        roleService.save(roleDTO);

        return ApiResult.success();
    }

    @PutMapping("")
    public ApiResult editRole(@RequestBody RoleDTO roleDTO) {
        roleService.update(roleDTO);

        return ApiResult.success();
    }
}
