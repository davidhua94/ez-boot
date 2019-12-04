package com.ezboot.core;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author David hua
 * @date 2019-11-17 16:57:14
 */
public class WebContext {
    public static HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }

    public static String getHeaderValue(String headName) {
        return getCurrentRequest().getHeader(headName);
    }

    public static String getCookieValue(String cookieKey) {
        Cookie[] cookies = getCurrentRequest().getCookies();
        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieKey)) {
                return cookie.getValue();
            }
        }

        return null;
    }
}
