package com.ezboot.controller.system;

import com.ezboot.core.ApiResult;
import com.ezboot.core.LocalMessage;
import com.ezboot.core.annotation.RequiresPermission;
import com.ezboot.core.base.PageResult;
import com.ezboot.core.constant.GlobalConstants;
import com.ezboot.core.util.JedisUtil;
import com.ezboot.core.util.Assert;
import com.ezboot.system.admin.dto.AdminListDTO;
import com.ezboot.system.admin.dto.AdminListQueryDTO;
import com.ezboot.system.admin.dto.AdminLoginDTO;
import com.ezboot.system.admin.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * hao
 */
@Api
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private LocalMessage localMessage;

    /**
     * 管理员登陆
     * @param loginRequest
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "管理员登陆")
    public ApiResult login(@RequestBody AdminLoginDTO loginRequest) {
        Assert.notBlank(loginRequest.getUsername(), localMessage.getMessage("MESS_USERNAME_NOT_BLANK"));
        Assert.notBlank(loginRequest.getPassword(), localMessage.getMessage("MESS_PASSWORD_NOT_BLANK"));

        String token = adminService.login(loginRequest);

        return ApiResult.success(token);
    }

    @PostMapping("/logout")
    public ApiResult logout(HttpServletRequest request) {
        String tokenValue = request.getHeader(GlobalConstants.ADMIN_TOKEN_KEY);

        JedisUtil.del(tokenValue);

        return ApiResult.success(Boolean.TRUE);
    }

    @GetMapping("/info")
    public ApiResult info() {
        log.info("test log traceId");

        Map<String, Object> data = new HashMap<>();
        data.put("name", "admin");
        data.put("avatar", "test4");

        // 这里需要转换perms结构，因为对于前端而已API形式的权限更容易理解
        data.put("perms", "*");
        data.put("roles", "admin");
        return ApiResult.success(data);
    }

    @GetMapping("/list")
    @RequiresPermission("system:admin:list")
    public ApiResult<PageResult<AdminListDTO>> list(AdminListQueryDTO queryDTO) {
        PageResult<AdminListDTO> adminList = adminService.pageList(queryDTO);
        return ApiResult.success(adminList);
    }


}
