package com.ezboot.system.timetask.repository;

import com.ezboot.system.timetask.entity.TimeTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author David hua
 * @date 2019-08-13 22:19
 */
@Repository
public interface TimeTaskRepository extends JpaRepository<TimeTask, Integer> {

    /**
     * 查询启用的task
     * @return
     */
    List<TimeTask> getAllByEnabledIsTrue();

    List<TimeTask> findAllByServerAndEnabledIsTrue(String server);
}
