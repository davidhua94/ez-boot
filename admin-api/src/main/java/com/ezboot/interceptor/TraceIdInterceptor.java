package com.ezboot.interceptor;

import com.ezboot.core.constant.GlobalConstants;
import com.ezboot.core.util.UUIDGenerator;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author David hua
 * @date 2019-08-16 22:01
 * 此拦截器设置traceId, 排在最前面
 * 方便追踪问题
 *
 * TODO 由于MDC是ThreadLocal实现的 子线程使用MDC.get取不到值
 * 尽量通过new CustomThread创建线程
 * 后续 添加CustomRunnable,CustomCallable, CustomThreadFactory支持
 *
 * @see com.ezboot.core.thread.CustomThread
 */
@Component
public class TraceIdInterceptor extends HandlerInterceptorAdapter {
    private static final String TRACE_ID_KEY = "traceId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = UUIDGenerator.shortStr();
        MDC.put(TRACE_ID_KEY, traceId);
        GlobalConstants.traceIdThreadLocal.set(traceId);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(TRACE_ID_KEY);
        GlobalConstants.traceIdThreadLocal.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
