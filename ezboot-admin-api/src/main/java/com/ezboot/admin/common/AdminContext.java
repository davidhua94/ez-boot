package com.ezboot.admin.common;

import com.ezboot.admin.system.constant.AdminCode;
import com.ezboot.core.CurrentAdmin;
import com.ezboot.core.WebContext;
import com.ezboot.core.constant.GlobalConstants;
import com.ezboot.core.exception.ServiceException;
import com.ezboot.core.util.GsonUtil;
import com.ezboot.core.util.JedisUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * @author David hua
 * @date 2019-11-17 17:01:30
 */
public class AdminContext {

    public static CurrentAdmin getCurrentAdmin() {
        String headerValue = WebContext.getHeaderValue(GlobalConstants.ADMIN_TOKEN_KEY);
        if (StringUtils.isBlank(headerValue)) {
            throw new ServiceException(AdminCode.ADMIN_NOT_LOGIN);
        }
        String userStr = JedisUtil.get(headerValue);
        if (StringUtils.isBlank(userStr)) {
            throw new ServiceException(AdminCode.ADMIN_NOT_LOGIN);
        }

        return GsonUtil.str2Obj(userStr, CurrentAdmin.class);
    }

    public static String getCurrentAdminUsername() {
        return getCurrentAdmin().getUsername();
    }

    public static Set<String> getCurrentAdminPermissions() {
        return getCurrentAdmin().getPermissions();
    }
}
