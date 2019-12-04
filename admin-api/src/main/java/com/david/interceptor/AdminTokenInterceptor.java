package com.david.interceptor;

import com.david.core.constant.GlobalConstants;
import com.david.core.util.JedisUtil;
import com.david.system.admin.constant.AdminCode;
import com.david.system.admin.exception.LoginException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author David hua
 * @date 2019-08-16 22:01
 * 管理后台TOKEN拦截
 */
@Component
public class AdminTokenInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(GlobalConstants.ADMIN_TOKEN_KEY);
        if (StringUtils.isBlank(token)) {
            throw new LoginException(AdminCode.USER_NOT_LOGIN);
        }

        String userInfoStr = JedisUtil.get(token);
        if (StringUtils.isBlank(userInfoStr)) {
            throw new LoginException(AdminCode.USER_NOT_LOGIN);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        JedisUtil.setExpire(GlobalConstants.ADMIN_TOKEN_KEY, GlobalConstants.ADMIN_TOKEN_EXPIRE);
    }
}
