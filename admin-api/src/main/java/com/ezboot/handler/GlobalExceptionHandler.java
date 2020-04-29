package com.ezboot.handler;

import com.ezboot.core.ApiResult;
import com.ezboot.core.LocalMessage;
import com.ezboot.core.base.MessageCode;
import com.ezboot.core.constant.GlobalConstants;
import com.ezboot.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author David hua
 * @date 2019-08-13 21:58
 * todo
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private LocalMessage messageUtil;

    @ExceptionHandler(value = Throwable.class)
    public ApiResult handleException(Throwable t) {
        /**
         * todo  记录错误日志到数据库
         */
        ApiResult apiResult;
        if (t instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) t;
            String code = serviceException.getCode();
            String message = serviceException.getMessage();

            if (StringUtils.isBlank(message)) {
                message = messageUtil.getMessage(code);
            }
            apiResult = ApiResult.error(code, message);
//            log.error("Something wrong, [code ={}, message={}]", apiResult.getCode(), apiResult.getMessage());
        } else {
            // 其他异常
            apiResult = ApiResult.error(MessageCode.INTERNAL_SERVER_ERROR,
                    messageUtil.getMessage(MessageCode.INTERNAL_SERVER_ERROR));
            log.error("Something wrong, [code={}, message={}]", apiResult.getCode(), apiResult.getMessage());
            log.error("Error: ", t);
        }

        /**
         * 如果是抛出异常时, 通过这里设置requestId
         * 正常情况下通过com.ezboot.aspect.TraceIdAspect设置requestId
         */
        if (StringUtils.isBlank(apiResult.getRequestId())) {
            apiResult.setRequestId(GlobalConstants.traceIdThreadLocal.get());
        }
        return apiResult;
    }

    /**
     * 参数校验失败
     */
    @ExceptionHandler(value = {BindException.class})
    public ApiResult handleBindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        return ApiResult.error(MessageCode.INVALID_PARAMS, bindingResult.getFieldError().getDefaultMessage());
    }

    /**
     * 参数校验失败
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ApiResult handleMethodArgumentException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        return ApiResult.error(MessageCode.INVALID_PARAMS, bindingResult.getFieldError().getDefaultMessage());
    }
}
