package com.ezboot.core;

import com.ezboot.core.annotation.CurrentUser;
import com.ezboot.core.constant.GlobalConstants;
import com.ezboot.core.util.GsonUtil;
import com.ezboot.core.util.JedisUtil;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author David hua
 * @date 2019-11-10 16:07:22
 */
@Component
public class CurrentAdminArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        CurrentUser parameterAnnotation = methodParameter.getParameterAnnotation(CurrentUser.class);
        return parameterAnnotation != null;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        ServletWebRequest request = (ServletWebRequest) nativeWebRequest;
        String adminToken = request.getHeader(GlobalConstants.ADMIN_TOKEN_KEY);
        String userInfoStr = JedisUtil.get(adminToken);
        return GsonUtil.str2Obj(userInfoStr, CurrentAdmin.class);
    }
}
