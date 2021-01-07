package com.ezboot.admin.system.service;

import com.ezboot.admin.system.dto.TimetaskListQueryDTO;
import com.ezboot.admin.system.entity.TimeTask;
import com.ezboot.core.base.PageResult;
import com.ezboot.core.base.service.BaseService;

import java.util.List;

/**
 * @author David hua
 * @date 2019-08-13 22:17
 */
public interface TimeTaskService extends BaseService<TimeTask> {

    PageResult<TimeTask> pageList(TimetaskListQueryDTO queryDTO);

    /**
     * 获取当前服务器上运行的job
     */
    List<TimeTask> getEnabledTaskByServer(String server);
}
