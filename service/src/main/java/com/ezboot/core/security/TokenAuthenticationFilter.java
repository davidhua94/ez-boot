package com.ezboot.core.security;

import com.ezboot.core.WebContext;
import com.ezboot.core.constant.GlobalConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author David hua
 * @date 2020-01-02 22:35:16
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        SecurityContext context = SecurityContextHolder.getContext();
        if (context.getAuthentication() == null ||
                !context.getAuthentication().isAuthenticated()) {

            String token = WebContext.getHeaderValue(GlobalConstants.ADMIN_TOKEN_KEY, httpServletRequest);
            if (StringUtils.isNotBlank(token)) {
                Authentication auth = new TokenAuthentication(token);
                context.setAuthentication(auth);
            }

            httpServletRequest.setAttribute("com.ezboot.core.security.TokenAuthenticationFilter.FILTERED", true);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
