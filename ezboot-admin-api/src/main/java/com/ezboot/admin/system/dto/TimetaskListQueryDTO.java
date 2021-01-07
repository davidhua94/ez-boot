package com.ezboot.admin.system.dto;

import com.ezboot.core.base.dto.SortedQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author David hua
 * @date 2019-12-29 22:30:13
 * 定时任务列表页查询参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TimetaskListQueryDTO extends SortedQuery {
    private String jobName;

    private Boolean status;
}
