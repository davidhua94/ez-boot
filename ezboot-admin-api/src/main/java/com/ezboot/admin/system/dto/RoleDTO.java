package com.ezboot.admin.system.dto;

import com.ezboot.core.base.dto.AbstractDTO;
import lombok.Data;

/**
 * @author David hua
 * @date 2019-11-17 17:32:01
 */
@Data
public class RoleDTO extends AbstractDTO {
    private Integer id;

    private String roleName;

    private String description;

    private Boolean enabled;
}
