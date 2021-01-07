package com.ezboot.admin.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author David hua
 * @date 2020-06-07 11：32
 * 此拦截器用于优化
 * 监控慢请求
 */
@Slf4j
@Component
public class PerformanceInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("REQUEST_START_TIME", startTime);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = (long) request.getAttribute("REQUEST_START_TIME");
        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;
        if (duration > 5000) {
            log.warn("[{}] {}, ip={}, time={}ms", request.getMethod(),request.getRequestURL(),
                    request.getRemoteAddr(), duration);
        } else {
            log.info("[{}] {}, ip={}, time={}ms", request.getMethod(),request.getRequestURL(),
                    request.getRemoteAddr(), duration);
        }

    }
}
