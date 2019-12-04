package com.david.controller.system;

import com.david.core.ApiResult;
import com.david.core.constant.GlobalConstants;
import com.david.core.util.JedisUtil;
import com.david.core.util.ValidateUtil;
import com.david.system.admin.dto.AdminLoginDTO;
import com.david.system.admin.service.AdminService;
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
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 管理员登陆
     * @param loginRequest
     * @return
     */
    @PostMapping("/login")
    public ApiResult login(@RequestBody AdminLoginDTO loginRequest) {
        ValidateUtil.notBlank(loginRequest.getUsername(), "username can't be empty");
        ValidateUtil.notBlank(loginRequest.getPassword(), "password can't be empty");

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


}
