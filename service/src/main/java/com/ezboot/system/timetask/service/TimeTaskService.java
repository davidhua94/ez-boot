package com.ezboot.system.timetask.service;

import com.ezboot.core.base.PageResult;
import com.ezboot.core.base.service.BaseService;
import com.ezboot.system.timetask.dto.TimetaskListQueryDTO;
import com.ezboot.system.timetask.entity.TimeTask;

/**
 * @author David hua
 * @date 2019-08-13 22:17
 */
public interface TimeTaskService extends BaseService<TimeTask> {

    PageResult<TimeTask> pageList(TimetaskListQueryDTO queryDTO);
}
