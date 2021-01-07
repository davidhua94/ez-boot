package com.ezboot.admin.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author David hua
 * 管理员登陆请求
 */
@Data
@ApiModel
public class AdminLoginDTO {

    @ApiModelProperty(name = "用户名")
    private String username;

    @ApiModelProperty(name = "密码")
    private String password;
}
