package com.ezboot.core.base;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author David hua
 * @date 2019-08-13 22:51
 * 分页结果
 * TODO 前面三个属性没啥用
 * totalCount 改成long
 */
@Data
@Builder
public class PageResult<T> implements Serializable {
    private int currentPage;
    private int pageSize;
    private int totalPage;
    private int totalCount;
    private List<T> data;
}
