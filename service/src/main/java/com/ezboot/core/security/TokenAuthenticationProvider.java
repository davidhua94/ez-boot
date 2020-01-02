package com.ezboot.core.security;

import com.ezboot.core.CurrentAdmin;
import com.ezboot.core.exception.ServiceException;
import com.ezboot.core.util.GsonUtil;
import com.ezboot.core.util.JedisUtil;
import com.ezboot.system.admin.constant.AdminCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author David hua
 * @date 2020-01-02 22:34:06
 */
public class TokenAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.isAuthenticated()) {
            return authentication;
        }

        Object credentials = authentication.getCredentials();
        String token = (String) credentials;

        String adminJsonStr = JedisUtil.get(token);
        if (StringUtils.isBlank(adminJsonStr)) {
            throw new ServiceException(AdminCode.USER_NOT_LOGIN);
        }

        CurrentAdmin currentAdmin = GsonUtil.str2Obj(adminJsonStr, CurrentAdmin.class);

        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
