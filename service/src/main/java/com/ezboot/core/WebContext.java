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
    /**
     * 获取当前HttpServletRequest对象
     * @return
     */
    public static HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }

    /**
     * 获取指定参数值
     * @param paramName
     * @return
     */
    public static String getParameter(String paramName) {
        return getCurrentRequest().getParameter(paramName);
    }

    /**
     * 获取指定请求头的值
     * @param headName
     * @return
     */
    public static String getHeaderValue(String headName) {
        return getCurrentRequest().getHeader(headName);
    }

    /**
     * 获取指定cookie值
     * @param cookieKey
     * @return
     */
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


    /**
     * 获取当前请求客户端IP
     * @return
     */
    public static String getRequestIp() {
        HttpServletRequest currentRequest = getCurrentRequest();

        return getIpAddress(currentRequest);
    }

    private static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(",")) {
            return ip.split(",")[0];
        } else {
            return ip;
        }
    }
}
