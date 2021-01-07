package com.ezboot.admin.system.dto;

import com.ezboot.core.base.dto.SortedQuery;
import lombok.Data;

/**
 * @author David hua
 * @date 2020-01-01 11:53:01
 * 管理员列表查询条件
 */
@Data
public class AdminListQueryDTO extends SortedQuery {
    private String username;
}
