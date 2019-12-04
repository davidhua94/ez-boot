package com.ezboot.controller;

import com.ezboot.core.ApiResult;
import com.ezboot.core.CurrentAdmin;
import com.ezboot.core.annotation.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author David hua
 * @date 2019-08-16 22:53
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("traceId")
    public ApiResult test(@CurrentUser CurrentAdmin user) {
        log.info("user ={}", user);
        log.info("test ===");
        return ApiResult.success();
    }
}