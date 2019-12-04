package com.ezboot.system.admin.dto;

import lombok.Data;

/**
 * @author David hua
 * 管理员登陆请求
 */
@Data
public class AdminLoginDTO {

    private String username;

    private String password;
}
