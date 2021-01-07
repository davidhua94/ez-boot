package com.ezboot.core.base.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author David hua
 * @date 2019-11-10 11:47:48
 * @desc 带排序的分页查询, 暂时只支持单字段排序
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SortedQuery extends PageQuery {

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序方式, ASC/DESC
     */
    private String sortType;
}
