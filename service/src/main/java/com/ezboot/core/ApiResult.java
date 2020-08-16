package com.ezboot.core;

import com.ezboot.core.base.MessageCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * Basic Api Response
 * @author david
 * @param <T>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> implements Serializable {
    private boolean success;
    private String code;
    private String message;
    public T data;
    private String requestId;

    private ApiResult (String code, boolean success) {
        this.code = code;
        this.success = success;
    }


    private ApiResult (String code, boolean success, String message) {
        this.code = code;
        this.success = success;
        this.message = message;
    }

    private ApiResult (String code, boolean success, T data) {
        this.code = code;
        this.success = success;
        this.data = data;
    }

    private ApiResult (String code, boolean success, String message, T data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    private ApiResult (String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> ApiResult<T> success() {
        return new ApiResult<T>(MessageCode.SUCCESS, true);
    }

    public static <T> ApiResult<T> error(String code) {
        return new ApiResult<T>(code, false);
    }

    public static <T> ApiResult<T> error(String code, String message) {
        return new ApiResult<T>(code, false, message);
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<T>(MessageCode.SUCCESS, true, "Success", data);
    }
}