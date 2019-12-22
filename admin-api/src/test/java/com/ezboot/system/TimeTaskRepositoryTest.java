package com.ezboot.system;

import com.ezboot.AdminApiApplicationTests;
import com.ezboot.system.timetask.entity.TimeTask;
import com.ezboot.system.timetask.repository.TimeTaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author David hua
 * @date 2019-08-13 22:37
 */
public class TimeTaskRepositoryTest extends AdminApiApplicationTests {
    @Autowired
    private TimeTaskRepository timeTaskRepository;

    @Test
    public void getByEnabled() {
        List<TimeTask> enabledTaskList = timeTaskRepository.getAllByEnabledIsTrue();
        Assert.assertNotNull(enabledTaskList);
    }
}
