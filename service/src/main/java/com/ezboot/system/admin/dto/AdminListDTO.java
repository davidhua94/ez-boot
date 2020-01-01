package com.ezboot.system.admin.dto;

import com.ezboot.core.base.dto.AbstractDTO;
import com.ezboot.core.constant.GlobalConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author David hua
 * @date 2020-01-01 11:55:58
 * 后台列表字段
 */
@Data
public class AdminListDTO extends AbstractDTO {
    private Integer id;

    private String username;

    private String lastLoginIp;

    @JsonFormat(pattern = GlobalConstants.DEFAULT_TIME_FORMAT,
            timezone = GlobalConstants.DEFAULT_TIMEZONE)
    private Date lastLoginTime;

    private List<String> roleList;

    private Boolean enabled;
}
