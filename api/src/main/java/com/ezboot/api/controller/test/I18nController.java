package com.ezboot.api.controller.test;

import com.ezboot.api.core.MessageUtil;
import com.ezboot.core.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author David hua
 * @date 2019-08-10 13:04
 */
@RestController
public class I18nController {

   @Autowired
   private MessageUtil messageUtil;

    @GetMapping("/test/i18n")
    public ApiResult testI18N() {
        return ApiResult.success(messageUtil.getMessage("200"));
    }
}
