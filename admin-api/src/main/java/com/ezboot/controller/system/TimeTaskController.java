package com.ezboot.controller.system;

import com.ezboot.core.ApiResult;
import com.ezboot.system.timetask.dto.TimetaskListQueryDTO;
import com.ezboot.system.timetask.service.TimeTaskService;
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
@RequestMapping("/job")
@RestController
public class TimeTaskController {

    @Autowired
    private TimeTaskService timeTaskService;

    @GetMapping("/list")
    public ApiResult list(TimetaskListQueryDTO queryDTO) {
        return ApiResult.success(timeTaskService.pageList(queryDTO));
    }
}
