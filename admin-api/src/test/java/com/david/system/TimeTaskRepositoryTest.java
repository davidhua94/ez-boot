package com.david.system;

import com.david.ManagementApplicationTests;
import com.david.system.timetask.entity.TimeTask;
import com.david.system.timetask.repository.TimeTaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author David hua
 * @date 2019-08-13 22:37
 */
public class TimeTaskRepositoryTest extends ManagementApplicationTests {
    @Autowired
    private TimeTaskRepository timeTaskRepository;

    @Test
    public void getByEnabled() {
        List<TimeTask> enabledTaskList = timeTaskRepository.getAllByEnabledIsTrue();
        Assert.assertNotNull(enabledTaskList);
    }
}
