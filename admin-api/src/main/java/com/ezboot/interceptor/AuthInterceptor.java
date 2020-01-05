package com.ezboot.interceptor;

import com.ezboot.context.AdminContext;
import com.ezboot.core.annotation.HasPermission;
import com.ezboot.core.exception.AccessDeniedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author David hua
 * @date 2019-08-16 22:01
 * 权限拦截
 */
@Slf4j
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return false;
        }

        Set<String> permissions = AdminContext.getCurrentAdminPermissions();
        if (permissions == null || permissions.isEmpty()) {
            throw new AccessDeniedException();
        }

        if (permissions.contains("*")) {
            return true;
        }

        /**
         * 拿到controller方法上的HasPermission注解，如果没有表示这个方法不需要任何权限
         */
        HandlerMethod handleMethod = (HandlerMethod) handler;
        HasPermission annotation = handleMethod.getMethod().getAnnotation(HasPermission.class);
        if (annotation == null) {
            return true;
        }

        /**
         * 拿到这个方法需要的权限
         */
        String needPermissions = annotation.needPermission();
        if (permissions.contains(needPermissions)) {
            return true;
        }

        log.debug("admin={}, access denied, need permission : {}", AdminContext.getCurrentAdminUsername(), needPermissions);
        throw new AccessDeniedException();

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
