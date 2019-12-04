package com.david.api.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author David hua
 * @date 2019-08-10 13:06
 */
@Component
public class MessageUtil {

    @Autowired
    private MessageSource messageSource;

    /**
     * 获取单个国际化翻译值
     */
    public String getMessage(String msgKey) {
        try {
            return messageSource.getMessage(msgKey, null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return msgKey;
        }
    }
}
