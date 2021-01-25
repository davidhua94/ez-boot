package com.ezboot.admin.system.controller;

import com.ezboot.admin.system.dto.TimetaskListQueryDTO;
import com.ezboot.admin.system.service.TimeTaskService;
import com.ezboot.core.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author David hua
 * @date 2019-08-13 22:09
 */
@Slf4j
@RestController
@RequestMapping("/job")
public class TimeTaskController {

    @Autowired
    private TimeTaskService timeTaskService;

    @GetMapping("/list")
    public ApiResult list(TimetaskListQueryDTO queryDTO) {
        return ApiResult.success(timeTaskService.pageList(queryDTO));
    }
}
