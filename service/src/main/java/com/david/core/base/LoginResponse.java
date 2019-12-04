package com.david.core.base;

import lombok.Getter;

@Getter
public enum LoginResponse {

    SUCCESS(1, "登录成功"),

    FAIL(2, "登录失败");

    private Integer erron;

    private String message;

    LoginResponse(Integer erron, String message){
        this.erron = erron;
        this.message = message;
    }

}
