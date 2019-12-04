package com.david.core.base;

import lombok.Data;

@Data
public class BaseResponse<T> {

    private Integer errno;
    private String message;
    private T data;

    public BaseResponse(LoginResponse loginResponse) {
        this.errno = loginResponse.getErron();
        this.message = loginResponse.getMessage();
    }

    public BaseResponse(Integer errno, String msg) {
        this.errno = errno;
        this.message = msg;
    }

    public BaseResponse(T data, LoginResponse loginResponse) {
        this.data = data;
        this.errno = loginResponse.getErron();
        this.message = loginResponse.getMessage();
    }

    public BaseResponse(Integer errno, String message, T data) {
        this.errno = errno;
        this.message = message;
        this.data = data;
    }


}
