package com.ezboot.controller;

import com.ezboot.core.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author David hua
 * @date 2019-08-17 00:34
 */
@RestController
public class IndexController {

    /**
     * 首页数据
     */
    @GetMapping("/dashboard")
    public ApiResult dashboard() {
        Map<String, Integer> dataMap = new HashMap<>();
        dataMap.put("userTotal", 999);
        dataMap.put("goodsTotal", 999);
        dataMap.put("productTotal", 999);
        dataMap.put("orderTotal", 999);
        // TODO
        return ApiResult.success(dataMap);
    }
}
