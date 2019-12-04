package com.david.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;

/**
 * @author David hua
 * @date 2019-08-10 13:08
 */
@Configuration
public class LocalResolverConfig {

    @Bean
    public LocaleResolver localeResolver () {
        FixedLocaleResolver localeResolver = new FixedLocaleResolver();
        /**
         * 暂时写死, 后面可以根据前端传过来的参数
         */
        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return localeResolver;
    }
}
