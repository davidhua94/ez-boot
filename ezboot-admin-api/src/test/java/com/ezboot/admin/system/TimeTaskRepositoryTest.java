package com.ezboot.admin.system;

import com.ezboot.admin.AdminApiApplicationTests;
import com.ezboot.admin.system.entity.TimeTask;
import com.ezboot.admin.system.repository.TimeTaskRepository;
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
