package com.ezboot.system.timetask.service.impl;

import com.ezboot.core.base.PageResult;
import com.ezboot.core.base.service.impl.BaseServiceImpl;
import com.ezboot.system.timetask.dto.TimetaskListQueryDTO;
import com.ezboot.system.timetask.entity.TimeTask;
import com.ezboot.system.timetask.repository.TimeTaskRepository;
import com.ezboot.system.timetask.service.TimeTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author David hua
 * @date 2019-12-29 22:33:40
 */
@Service
public class TimeTaskServiceImpl extends BaseServiceImpl<TimeTask> implements TimeTaskService {
    @Autowired
    private TimeTaskRepository timeTaskRepository;

    @Override
    public PageResult<TimeTask> pageList(TimetaskListQueryDTO queryDTO) {

        return null;
    }
}
