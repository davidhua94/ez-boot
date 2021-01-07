package com.ezboot.admin.system.service.impl;

import com.ezboot.admin.system.dto.TimetaskListQueryDTO;
import com.ezboot.admin.system.entity.TimeTask;
import com.ezboot.admin.system.repository.TimeTaskRepository;
import com.ezboot.admin.system.service.TimeTaskService;
import com.ezboot.core.base.PageResult;
import com.ezboot.core.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<TimeTask> getEnabledTaskByServer(String server) {
        return timeTaskRepository.findAllByServerAndEnabledIsTrue(server);
    }
}
