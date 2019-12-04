package com.david.context;

import com.david.core.CurrentAdmin;
import com.david.core.WebContext;
import com.david.core.constant.GlobalConstants;
import com.david.core.exception.ServiceException;
import com.david.core.util.GsonUtil;
import com.david.core.util.JedisUtil;
import com.david.system.admin.constant.AdminCode;
import org.apache.commons.lang3.StringUtils;

/**
 * @author David hua
 * @date 2019-11-17 17:01:30
 */
public class AdminContext {

    public static CurrentAdmin getCurrentAdmin() {
        String headerValue = WebContext.getHeaderValue(GlobalConstants.ADMIN_TOKEN_KEY);
        if (StringUtils.isBlank(headerValue)) {
            throw new ServiceException(AdminCode.USER_NOT_LOGIN);
        }
        String userStr = JedisUtil.get(headerValue);
        if (StringUtils.isBlank(userStr)) {
            throw new ServiceException(AdminCode.USER_NOT_LOGIN);
        }

        return GsonUtil.str2Obj(userStr, CurrentAdmin.class);
    }

    public static String getCurrentAdminUsername() {
        return getCurrentAdmin().getUsername();
    }
}
