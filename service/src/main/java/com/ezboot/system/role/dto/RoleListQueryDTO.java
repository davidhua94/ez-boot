package com.ezboot.system.role.dto;

import com.ezboot.core.base.dto.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author David hua
 * @date 2019-11-10 11:55:50
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleListQueryDTO extends PageQuery {
    private String name;
}
